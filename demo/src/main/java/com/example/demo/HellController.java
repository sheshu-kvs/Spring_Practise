package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellController {

    @GetMapping("/a1")
    public String HiControl(){
        return "Hi How are you";
    }
    
}
