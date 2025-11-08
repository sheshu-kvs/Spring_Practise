package com.demo.foodwebapplication.Controller;

import java.util.List;
import java.util.Map;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.foodwebapplication.Service.userService;
import com.demo.foodwebapplication.model.user;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*") 
public class userController {

    @Autowired
    public userService userService;


    //register the new User
    @PostMapping("/register")
    public user regsiterUser(@RequestBody user u) {
        return userService.saveUser(u);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("name");
        String password = loginData.get("password");

        try {
            user loggedInUser = userService.loginUser(username, password);
            return ResponseEntity.ok(loggedInUser);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<user> getAllUser() {
        return userService.getAll();
    }

}
