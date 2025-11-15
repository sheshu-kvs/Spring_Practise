package com.demo.foodwebapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // 👈 Add this line to turn Caching on
public class FoodwebapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodwebapplicationApplication.class, args);
		System.out.println("==========The Food Application=========");

	}
}
