package com.demo.loginvalidation.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.loginvalidation.Service.UsersService;
import com.demo.loginvalidation.model.Users;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private UsersService userservice;

    public RegistrationController(UsersService usersservice){
        this.userservice=usersservice;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody Users registerrequest) {
        try {
            Users savedUser = userservice.saveUser(registerrequest);
            return new ResponseEntity<>("User Successfully Registed "+savedUser , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to register" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
