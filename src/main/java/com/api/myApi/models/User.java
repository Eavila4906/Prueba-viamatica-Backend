/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.myApi.models;

import com.api.myApi.helpers.consecutiveDigitsHelp;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author erik-avila
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;

    @Column(name = "id_card", length = 10, unique = true, nullable = false)
    @NotBlank(message = "This field is required")
    @Pattern(regexp = "\\d{10}", message = "ID card must be 10 characters long")
    @consecutiveDigitsHelp(message = "The ID card number cannot contain 4 digits repeated consecutively.")
    private String idCard;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "This field is required")
    @Size(min = 1, max = 50, message = "Name must be 50 characters long")
    private String name;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "This field is required")
    @Size(min = 1, max = 50, message = "Lastname must be 50 characters long")
    private String lastname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-dd-MM")
    private Date birthdate;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "This field is required")
    @Size(min = 1, max = 50, message = "Username must be 50 characters long")
    private String username;

    @Column(length = 150, unique = true)
    private String mail;

    @Column(length = 255, nullable = false)
    @NotBlank(message = "This field is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=\\-\\[\\]{};':\"\\\\|,.<>/?]).*$", message = "Password does not meet requirements")
    private String password;

    @Column(name = "active_session", length = 1)
    private String activeSession;

    @Column(length = 20)
    private String status;

    @Column(name = "failed_attempts", columnDefinition = "int default 0")
    private int failedAttempts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {
        @JoinColumn(name = "idUser")}, inverseJoinColumns = {
        @JoinColumn(name = "idRole")}
    )
    @JsonBackReference
    private Set<Role> roles;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActiveSession() {
        return activeSession;
    }

    public void setActiveSession(String activeSession) {
        this.activeSession = activeSession;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    
}
