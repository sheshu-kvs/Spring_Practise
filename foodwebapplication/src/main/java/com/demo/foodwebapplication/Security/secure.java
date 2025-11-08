// package com.demo.foodwebapplication.Security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {



//     @Bean   
//     public SecurityFilterChain securityfilterchain (HttpSecurity http) throws Exception{
//         return http

//         .csrf(AbstractHttpConfigurer::disable)
//         .cors(cors -> cors.disable()) // 
//        .authorizeHttpRequests(register->
//         register.requestMatchers("/user/register").permitAll()
//         .anyRequest().authenticated()
//        )
//         .build();

//     }


    
// }


//         // "/food/all","/food/register","/food/login","/res/all","/res/add","res/del",/food/restaurant/{id}","/food/uploadImage/{id}","/res/uploadImage/{id}"
