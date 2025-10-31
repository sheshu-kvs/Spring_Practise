package com.example.springdependencyinjection;

import org.springframework.stereotype.Component;

@Component
public interface Engine {

      default  void petrolEngine(){
         System.out.println("An Car Run With the Petrol");
       }

        void dieselEngine();
}
