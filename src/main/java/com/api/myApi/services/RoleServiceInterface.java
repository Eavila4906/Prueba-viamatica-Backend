/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.api.myApi.services;

import com.api.myApi.models.Role;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author erik-avila
 */
public interface RoleServiceInterface {

    public List<Role> findAll();

    public Optional<Role> findById(Long id);

    public Role save(Role role);

    public Role edit(Long id, Role role);

    public void delete(Long id);

}
