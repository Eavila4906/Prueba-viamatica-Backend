/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.myApi.controllers;

import com.api.myApi.DTO.AuthDTO;
import com.api.myApi.models.Session;
import com.api.myApi.models.User;
import com.api.myApi.services.RoleServiceImplementation;
import com.api.myApi.services.SessionServiceImplementation;
import com.api.myApi.services.UserServiceImplementation;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author erik-avila
 */
@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserServiceImplementation UserService;

    @Autowired
    private RoleServiceImplementation RoleService;

    @Autowired
    private SessionServiceImplementation SessionService;

    private Map<String, Object> response = new HashMap<>();

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthDTO data) {
        String user = data.getUser();
        String password = data.getPassword();
        User userLogin = new User();

        //Verification if exits user
        if (!UserService.findByUsername(user).isPresent()) {
            response.clear();
            response.put("message", "User not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        //Login verification by username or email
        if (UserService.findByUsername(user).isPresent()) {
            Optional<User> optionalUser = UserService.findByUsername(user);
            userLogin = optionalUser.get();
        } else {
            Optional<User> optionalMail = UserService.findByMail(user);
            userLogin = optionalMail.get();
        }

        //Blocked user verification
        if (userLogin.getStatus() != null && userLogin.getStatus().equalsIgnoreCase("LOCKED")) {
            response.clear();
            response.put("message", "This user is blocked");
            return ResponseEntity.badRequest().body(response);
        }

        //Access Password Validation
        int attempts = UserService.findFailedAttemptsByUser(userLogin.getIdUser());
        if (!userLogin.getPassword().equals(password)) {
            userLogin.setFailedAttempts(attempts + 1);
            UserService.save(userLogin);
            if (attempts + 1 >= 3) {
                userLogin.setStatus("LOCKED");
                UserService.save(userLogin);
                response.clear();
                response.put("message", "This user has been blocked.");
                return ResponseEntity.badRequest().body(response);
            }
            response.clear();
            response.put("message", "Incorrect password.");
            return ResponseEntity.badRequest().body(response);
        }

        //Register user session
        Session session = new Session();
        session.setUser(userLogin);

        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        session.setStartDate(date);

        SessionService.save(session);

        //Register active session
        userLogin.setActiveSession("1");
        userLogin.setStatus("1");
        UserService.save(userLogin);

        //Login user data
        List<Object> roles = UserService.findRolesByIdUser(userLogin.getIdUser());
        response.clear();
        response.put("message", "Login successfull");
        response.put("idUser", userLogin.getIdUser());
        response.put("username", userLogin.getUsername());
        response.put("name", userLogin.getName());
        response.put("lastname", userLogin.getLastname());
        response.put("roles", roles);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, Long> data) {
        Long user = data.get("idUser");

        Optional<User> userLogout = UserService.findById(user);

        if (!userLogout.isPresent()) {
            response.clear();
            response.put("message", "User not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Session session = SessionService.findLastSessionByIdUser(userLogout.get().getIdUser());

        //Register logout in user sessions
        if (session != null) {
            LocalDateTime localDateTime = LocalDateTime.now();
            Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            session.setDeparture_date(date);
            SessionService.save(session);
        }

        //Register session inactiva with 0
        userLogout.get().setActiveSession("0");
        userLogout.get().setStatus("0");
        UserService.save(userLogout.get());

        response.clear();
        response.put("message", "Logout successfull.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/sessions_details")
    public ResponseEntity<?> sessionsDetails(@RequestBody Map<String, String> data) {
        String idUser = data.get("idUser");

        Optional<User> usr = UserService.findById(Long.parseLong(idUser));

        if (!usr.isPresent()) {
            response.clear();
            response.put("message", "User not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Session session = SessionService.findPenultimateSessionByIdUser(usr.get().getIdUser());
        int attempts = UserService.findFailedAttemptsByUser(usr.get().getIdUser());

        //Data user sessions
        response.clear();
        response.put("start_date", session.getStartDate());
        response.put("departure_date", session.getDeparture_date());
        response.put("attempts", attempts);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
