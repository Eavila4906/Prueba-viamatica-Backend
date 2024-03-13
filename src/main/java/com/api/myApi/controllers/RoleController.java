/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.myApi.controllers;

import com.api.myApi.models.Role;
import com.api.myApi.services.RoleServiceImplementation;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
public class RoleController {

    @Autowired
    private RoleServiceImplementation service;
    private Map<String, Object> response = new HashMap<>();

    @GetMapping("/roles")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping("/role/save")
    public ResponseEntity<?> save(@Valid @RequestBody Role role) {
        Role saved = service.save(role);
        response.clear();
        response.put("message", "Role registered successfully");
        response.put("Role", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/role/edit/{id}")
    public ResponseEntity<?> edit(@Valid @PathVariable Long id, @RequestBody Role role) {
        Role edited = service.edit(id, role);
        response.clear();
        response.put("message", "Role edited successfully");
        response.put("Role", edited);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @DeleteMapping("/role/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Role> role = service.findById(id);
        
        if (!role.isPresent()) { 
            response.clear();
            response.put("message", "Role not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        
        service.delete(id);
        
        response.clear();
        response.put("message", "Role deleted successful");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
