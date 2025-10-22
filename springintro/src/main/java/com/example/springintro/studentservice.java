package com.example.springintro;

import org.springframework.stereotype.Component;

@Component
public class studentservice {
    public String dispService(){
        return "StudentService using the StudentReposiotory...";
    }
}
