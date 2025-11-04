

package com.demo.foodwebapplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.foodwebapplication.Service.userService;
import com.demo.foodwebapplication.model.user;

@RestController
@RequestMapping("/food")
public class userController {

    @Autowired
    public userService userService;
    

     @GetMapping("/all")
     public List<user> getAllUser(){
        return userService.getAll();
     }

    
}
