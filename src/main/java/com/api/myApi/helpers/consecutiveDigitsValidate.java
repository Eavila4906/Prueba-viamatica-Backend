/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.myApi.helpers;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 *
 * @author erik-avila
 */
public class consecutiveDigitsValidate implements ConstraintValidator<consecutiveDigitsHelp, String> {
    
    @Override
    public void initialize(consecutiveDigitsHelp constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() < 4) {
            return true;
        }

        char[] chars = value.toCharArray();
        int count = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            if (count == 4) {
                return false;
            }
        }
        return true;
    }

}
