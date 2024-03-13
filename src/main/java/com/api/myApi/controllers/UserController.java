/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.myApi.controllers;

import com.api.myApi.models.Role;
import com.api.myApi.models.User;
import com.api.myApi.services.RoleServiceImplementation;
import com.api.myApi.services.UserServiceImplementation;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author erik-avila
 */
@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImplementation UserService;

    @Autowired
    private RoleServiceImplementation RoleService;

    private Map<String, Object> response = new HashMap<>();

    @GetMapping("/users")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(UserService.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(UserService.findById(id));
    }

    @PostMapping("/user/save")
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
        try {
            if (UserService.findByUsername(user.getUsername()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This username is already registered");
            }

            Set<Role> roles = new HashSet<>();
            for (Role role : user.getRoles()) {
                Optional<Role> roleOptional = RoleService.findById(role.getIdRole());
                roleOptional.ifPresent(roles::add);
            }

            User saved = UserService.save(user);
            response.clear();
            response.put("message", "User registered successfully");
            response.put("user", saved);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/user/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody Map<String, String> user) {
        Optional<User> userValidate = UserService.findById(id);

        if (!userValidate.isPresent()) {
            response.clear();
            response.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        User edited = UserService.edit(id, user);
        response.clear();
        response.put("message", "User edited successfully");
        response.put("Role", edited);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<User> userValidate = UserService.findById(id);

        if (!userValidate.isPresent()) {
            response.clear();
            response.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        UserService.delete(id);
        response.clear();
        response.put("message", "User deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
