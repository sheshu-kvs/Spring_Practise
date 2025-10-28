package com.example.studentmanagement.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.studentmanagement.model.student;
import com.example.studentmanagement.repository.studentrepo;


import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class studentservice implements  UserDetailsService {

    @Autowired
    private studentrepo repo;


    @Autowired
private PasswordEncoder passwordEncoder;



    public student save(student stu){
       stu.setPassword(passwordEncoder.encode(stu.getPassword()));
       return repo.save(stu);
    }


    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException{
        Optional<student> stu =repo.findByName(name);


        if(stu.isEmpty()){
            throw new  UsernameNotFoundException(name); 
        }

        var stuObj = stu.get();

         String role = stuObj.getRole(); // assume "ADMIN" or "USER" stored in DB

   
    if (role == null || role.isBlank()) {
        role = "USER";
    }


        return User.builder()
            .username(stuObj.getName())
            .password(stuObj.getPassword())
            .roles(role)
            .build(); 
    }



}
