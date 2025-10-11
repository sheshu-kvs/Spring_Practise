package com.demo.loginvalidation.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.loginvalidation.Service.UsersService;
import com.demo.loginvalidation.model.Users;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UsersService userService;

    public LoginController(UsersService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Users> getallUser() {
        return userService.getallUsers();
    }


    @PostMapping
    public ResponseEntity<String> login(@RequestBody Users loginRequest) {
        boolean isValid = userService.validateLogin(loginRequest.getUsername(), loginRequest.getPassword());
        if (isValid) {
            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }
}
