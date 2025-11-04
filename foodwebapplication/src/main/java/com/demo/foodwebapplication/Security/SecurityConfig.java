package com.demo.foodwebapplication.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean   
    public SecurityFilterChain securityfilterchain (HttpSecurity http) throws Exception{
        return http

        .csrf(AbstractHttpConfigurer::disable)

       .authorizeHttpRequests(register->{
        register.requestMatchers("/food/all").permitAll();
       }
       )
        .build();

    }


    
}
