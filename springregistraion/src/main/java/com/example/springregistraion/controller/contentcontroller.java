package com.example.springregistraion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class contentcontroller {
    

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/req")
    public String signUp(){
        return "signup";
    }

    @GetMapping("/index")
   public String GetDat(){
    return "index";
   } 
}
