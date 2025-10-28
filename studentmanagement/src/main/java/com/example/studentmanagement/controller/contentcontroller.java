package com.example.studentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class contentcontroller {

    @GetMapping("/login")
    public String loginPage(){
        return"login";
    }
    @GetMapping("/signup")
    public String signUp(){
        return"signup";
    }
    //
    @GetMapping("/admin")
    public String adminpage(){
        return "admin";
    }
    @GetMapping("/user")
    public String userpage(){
        return "user";
    }
    
}
