package com.example.studentjwtexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentjwtexample.model.student;
import com.example.studentjwtexample.repo.studentRepo;

@Service
public class studentservice {

    @Autowired
    public studentRepo studentrepo;

    public student registerNewUser(student stu){
        return studentrepo.save(stu);
    }
    
}
