/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.myApi.services;

import com.api.myApi.models.Role;
import com.api.myApi.repositories.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author erik-avila
 */

@Service
public class RoleServiceImplementation implements RoleServiceInterface {
    
    @Autowired
    private RoleRepository repository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return repository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findById(Long id) {
        return repository.findById(id);
    }
    
    @Override
    @Transactional()
    public Role save(Role role) {
        return repository.save(role);
    }
    
    @Override
    @Transactional()
    public Role edit(Long id, Role role) {
        Role roleValidate = repository.findById(id).orElse(null);
        
        if (roleValidate != null) {
            role.setRole(role.getRole());
            role.setIdRole(id);
            return repository.save(role);
        } else {
            throw new EntityNotFoundException("User not found");
        }
    }
    
    @Override
    @Transactional()
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
}
