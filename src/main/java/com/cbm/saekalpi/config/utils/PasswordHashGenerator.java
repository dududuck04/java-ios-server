package com.cbm.saekalpi.config.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "";
        String hashedPassword = encoder.encode(password);
        System.out.println("Hashed password: " + hashedPassword);
    }
}
