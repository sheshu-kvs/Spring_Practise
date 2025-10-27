package com.example.springregistraion.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springregistraion.model.MyAppuser;
import com.example.springregistraion.service.MyAppuserService;

@RestController
public class forgotController {

    private MyAppuserService userservice;

  @Autowired
  private PasswordEncoder passwordEncoder;

    public forgotController(MyAppuserService userservice){
         this.userservice = userservice;
    }

    //  @PostMapping("/req/signup")
    // public MyAppuser newUser(@RequestBody MyAppuser user){
    //   user.setPassword(passwordEncoder.encode(user.getPassword()));
    //     return service.saveNewUser(user);
    // }

// Spring receives this JSON in the @RequestBody.
// The controller uses the UserService to:
// Find the user by username (findByUsername)
// Encode the new password using passwordEncoder.encode()
// Save the updated user
// Then it returns a success response (or redirects to login page).


@PostMapping("/req/updatepwd")
public String UpdatePwd(@RequestBody MyAppuser User){
   MyAppuser UpdatedUser = userservice.findbyusername(User.getUsername());

   if(UpdatedUser == null){
    return "User Not Found ";
   }
   UpdatedUser.setPassword(passwordEncoder.encode(User.getPassword()));
   userservice.saveNewUser(UpdatedUser);
   return "passWord Updated Successfully ";
}

}
