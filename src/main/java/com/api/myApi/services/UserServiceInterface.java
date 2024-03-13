/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.api.myApi.services;

import com.api.myApi.models.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author erik-avila
 */
public interface UserServiceInterface {

    public Iterable<User> findAll();

    public Optional<User> findById(Long id);

    public User save(User user);

    public User edit(Long id, Map<String, String> user);

    public void delete(Long id);
    
    //More functions
    public Optional<User> findByMail(String mail);
    
    public Optional<User> findByUsername(String username);
    
    public List<Object> findRolesByIdUser(Long idUser);
    
    public Integer findFailedAttemptsByUser(Long idUser);
    
}
