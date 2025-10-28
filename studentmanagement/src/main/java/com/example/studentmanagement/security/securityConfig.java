package com.example.studentmanagement.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationProvider;


import com.example.studentmanagement.service.studentservice;

@Configuration
@EnableWebSecurity
public class securityConfig {
    




    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    } 

    @Bean
    public AuthenticationProvider authenticationProvider(studentservice servicecls){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(servicecls);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    @Bean
    public SecurityFilterChain securityfilterchain(HttpSecurity httpsecurity) throws Exception{
        return httpsecurity
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(register ->
           register.requestMatchers("/admin/**").hasRole("ADMIN")
          .requestMatchers("/signup").permitAll()
          .requestMatchers("/user/**").hasAnyRole("ADMIN","USER")
          .requestMatchers("/upload").permitAll()
          .anyRequest().authenticated()
        )


        .formLogin(httpform->
            httpform.loginPage("/login").permitAll()
            .defaultSuccessUrl("/index",true)

            .successHandler((request,response,authentication)->{
            Collection <? extends GrantedAuthority> authority=authentication.getAuthorities();
            for(GrantedAuthority authority2:authority){
                String role=authority2.getAuthority();
                System.out.println("Role"+role);
                if(role.equals("ROLE_ADMIN")){
                    response.sendRedirect("/admin");
                    return;
                }
                else if(role.equals("ROLE_USER")){
                    response.sendRedirect("/user");
                    return;
                }
            }
            response.sendRedirect("/index");
        })
        )

        .build();
    }


   
    

}
