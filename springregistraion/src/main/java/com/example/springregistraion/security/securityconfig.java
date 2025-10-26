package com.example.springregistraion.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.springregistraion.service.MyAppuserService;

@Configuration
//This will tells the this class includes the importan security details...
// 
@EnableWebSecurity
//this enables the spring security... 
public class securityconfig {

    @Autowired
    private MyAppuserService userservice;



    @Bean
    public UserDetailsService userDetailsService(MyAppuserService userservice){
        return userservice;
    }

    @Bean
    public AuthenticationProvider AuthenticationProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userDetailsService);
        // if we not write this means , spring plain password to the encoded password..
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }   


    @Bean

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    



    @Bean
    // we need to define our security roles
    public  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
        // it avoids the cyber security attacks
        // it is not recomded for the production
        // 
        .csrf(AbstractHttpConfigurer::disable)


         .authorizeHttpRequests(register->{
                // specifying the url for the registration page..
                register.requestMatchers("/req/**").permitAll();
                // user can frelly register without the authenticartion
                register.anyRequest().authenticated();
            })



        // how user can login our webapge
            .formLogin(httpForm ->{
                httpForm.loginPage("/login").permitAll();
                //after user login the page means it will redirect to this page
                httpForm.defaultSuccessUrl("/index",true);
              })
            //   we need to use the registration page without the authentication..

           



              .build();
    }
    
}
