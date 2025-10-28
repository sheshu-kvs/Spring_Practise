package com.example.studentmanagement.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class adminEncodedPassword {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encoded = encoder.encode("admin1234");
        System.out.println("Password "+encoded);
    }
}
