package com.example.studentprac.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentprac.model.student;
import com.example.studentprac.service.studentservise;

@RestController
public class RestControllerClass {

    @Autowired
    public studentservise serviceclass;

    @GetMapping("/users/dashboard")
    public ResponseEntity<student> getDashboardDetails(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
         student stu = serviceclass.getStudent(username);
         return ResponseEntity.ok(stu);
    }

    @GetMapping("/admins/dashboard")
    public List<student> getAllAdminDashBoard(){
        return serviceclass.getAll();
         
    }

 
    
 
}
