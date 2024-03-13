/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.myApi.services;

import com.api.myApi.models.Session;
import com.api.myApi.repositories.SessionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author erik-avila
 */
@Service
public class SessionServiceImplementation implements SessionServiceInterface {
    
    @Autowired
    private SessionRepository repository;

    @Override
    public List<Session> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session findSesionById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session save(Session session) {
        return repository.save(session);
    }

    @Override
    public Session edit(Long id, Session session) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //More functions
    @Override
    public Session findLastSessionByIdUser(Long idUser) {
        return repository.findLastSessionByIdUser(idUser);
    }

    @Override
    public Session findPenultimateSessionByIdUser(Long idUser) {
        return repository.findPenultimateSessionByIdUser(idUser);
    }
    
}
