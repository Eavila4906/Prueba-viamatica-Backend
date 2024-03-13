/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.myApi.DTO;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author erik-avila
 */
public class AuthDTO {

    @NotBlank(message = "This field is required")
    private String user;

    @NotBlank(message = "This field is required")
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
