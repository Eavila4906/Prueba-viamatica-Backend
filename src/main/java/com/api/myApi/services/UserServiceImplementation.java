/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.myApi.services;

import com.api.myApi.helpers.MailHelper;
import com.api.myApi.models.User;
import com.api.myApi.repositories.UserRepository;
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
public class UserServiceImplementation implements UserServiceInterface {

    @Autowired
    private UserRepository repository;

    MailHelper mail = new MailHelper();

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional()
    public User save(User user) {
        String mail = this.mail.generateMail(user.getName(), user.getLastname());

        int count = 0;
        String originMail = mail;

        while (this.findByMail(mail).isPresent()) {
            count++;
            mail = originMail.split("@")[0] + count + "@" + originMail.split("@")[1];
        }
        user.setMail(mail);

        return repository.save(user);
    }

    @Override
    @Transactional()
    public User edit(Long id, User user) {
        user.setIdUser(id);
        return repository.save(user);
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        repository.deleteById(id);
    }

    //More functions
    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByMail(String mail) {
        return repository.findByMail(mail);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object> findRolesByIdUser(Long id) {
        return repository.findRolesByIdUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer findFailedAttemptsByUser(Long idUser) {
        return repository.findFailedAttemptsByUser(idUser);
    }

}
