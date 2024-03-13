/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.api.myApi.services;

import com.api.myApi.models.Session;
import java.util.List;

/**
 *
 * @author erik-avila
 */
public interface SessionServiceInterface {

    public List<Session> findAll();

    public Session findSesionById(Long id);

    public Session save(Session session);

    public Session edit(Long id, Session session);

    public void delete(Long id);
    
    //More functions
    public Session findLastSessionByIdUser(Long idUser);
    
    public Session findPenultimateSessionByIdUser(Long idUser);
}
