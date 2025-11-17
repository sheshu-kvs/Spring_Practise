package com.example.studentjwtexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentjwtexample.model.student;
import com.example.studentjwtexample.service.studentservice;

@RestController
@RequestMapping("/stu")
public class studentController {
    @Autowired
    public studentservice studentService;

    @PostMapping
    public student insertNewStudent(@RequestBody student stu){
        return studentService.registerNewUser(stu);
    }


}
