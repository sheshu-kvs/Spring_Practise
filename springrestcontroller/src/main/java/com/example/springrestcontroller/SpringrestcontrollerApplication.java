package com.example.springrestcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringrestcontrollerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringrestcontrollerApplication.class, args);
		System.out.println("=============This is From the RestController Class=============");
	}
}
