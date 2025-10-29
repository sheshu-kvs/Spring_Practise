package com.example.studentprac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class contentcontroller {

    @GetMapping("/login")
    public String loginpage(){
        return "login";
    }

    @GetMapping("/admin")
    public String adminpage(){
        return"admin";
    }
    @GetMapping("/user")
    public String userpage(){
        return "user";
    } 
    @GetMapping("/signup")
    public String singupage(){
        return "signup";
    }
    

}
