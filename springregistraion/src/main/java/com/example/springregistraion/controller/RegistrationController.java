package com.example.springregistraion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.example.springregistraion.model.MyAppuser;
import com.example.springregistraion.service.MyAppuserService;

@RestController

public class RegistrationController {

  private MyAppuserService service;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  public  RegistrationController(MyAppuserService service){
           this.service=service;
  }



   

    @PostMapping("/req/signup")
    public MyAppuser newUser(@RequestBody MyAppuser user){
      user.setPassword(passwordEncoder.encode(user.getPassword()));
        return service.saveNewUser(user);
    }

 
    
}
