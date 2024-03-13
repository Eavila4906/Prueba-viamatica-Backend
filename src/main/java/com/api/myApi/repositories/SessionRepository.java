/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.api.myApi.repositories;

import com.api.myApi.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author erik-avila
 */

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query(value = "SELECT * FROM user_sessions WHERE id_user = :idUser ORDER BY start_date DESC LIMIT 1", nativeQuery = true)
    public Session findLastSessionByIdUser(Long idUser);

    @Query(value = "SELECT * FROM user_sessions WHERE id_user = :idUser ORDER BY start_date DESC LIMIT 1 OFFSET 2", nativeQuery = true)
    public Session findPenultimateSessionByIdUser(Long idUser);

}
