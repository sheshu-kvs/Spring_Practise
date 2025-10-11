package com.demo.loginvalidation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.loginvalidation.Repository.userRepo;
import com.demo.loginvalidation.model.user;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private userRepo userrepo;


    @GetMapping
    public List<user> getallEmp(){
        return userrepo.findall();
    }

}
