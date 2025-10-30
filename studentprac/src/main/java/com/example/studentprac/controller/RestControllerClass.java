package com.example.studentprac.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

       @GetMapping("/users/resume/{filename}")
public ResponseEntity<Resource> viewfile(@PathVariable String filename) throws IOException {
    Resource resource = serviceclass.getFileasResource(filename);

    return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
            .body(resource);
}


//for the downloading we are the same as the above controller...

@GetMapping("/users/download/{filename}")
public ResponseEntity<Resource> downloadfile(@PathVariable String filename) throws IOException{
    Resource resource  = serviceclass.getFileasResource(filename);
    return ResponseEntity.ok()
    .contentType(MediaType.APPLICATION_PDF)
    .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\""+ filename +"\"")
    .body(resource);

} 

   } 

    
    
