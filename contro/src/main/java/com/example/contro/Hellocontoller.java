package com.example.contro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //tells this is the controlller
public class Hellocontoller {

    @GetMapping("/")  //Get the details of the controller..
    public String hello(){
        return"Hello Welcome to the SpringBoot";
    }

    
}
