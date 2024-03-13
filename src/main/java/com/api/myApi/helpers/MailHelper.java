/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.myApi.helpers;

import org.springframework.context.annotation.Configuration;

/**
 *
 * @author erik-avila
 */
@Configuration
public class MailHelper {
    public String generateMail(String names, String lastnames) {
        String username = names.toLowerCase().substring(0, 1) + lastnames.toLowerCase();
        return username + "@mail.com";
    }
}
