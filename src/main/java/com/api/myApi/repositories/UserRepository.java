/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.api.myApi.repositories;

import com.api.myApi.models.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author erik-avila
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE users.username =:username", nativeQuery = true)
    public Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE users.mail = :mail", nativeQuery = true)
    public Optional<User> findByMail(String mail);

    @Query(value = "SELECT failed_attempts FROM users WHERE id_user = :idUser", nativeQuery = true)
    public Integer findFailedAttemptsByUser(Long idUser);

    @Query(value = "SELECT r.id_role, r.role " + "FROM users u " + "JOIN user_roles ur ON u.id_user=ur.id_user " + "JOIN roles r ON r.id_role=ur.id_role " + "WHERE u.id_user = :idUser", nativeQuery = true)
    public List<Object> findRolesByIdUser(Long idUser);

}
