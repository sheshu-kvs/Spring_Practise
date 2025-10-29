package com.example.studentprac.security;


import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.studentprac.service.studentservise;


@Configuration
@EnableWebSecurity
public class securityConfig {

    

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    } 




    @Bean 
    public AuthenticationProvider authenticationprovider(studentservise serviceclas){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(serviceclas);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    } 

    @Bean 
    public SecurityFilterChain securtiyfilterchain (HttpSecurity httpsecurity) throws Exception{
        return httpsecurity
        .csrf(AbstractHttpConfigurer::disable)


        .authorizeHttpRequests(register ->
           register.requestMatchers("/admin/**").hasRole("ADMIN")
           .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
           .requestMatchers("/signup").permitAll()
           .requestMatchers("/upload").permitAll()
           .anyRequest().authenticated()
         )

         .formLogin(httpform ->
            httpform.loginPage("/login").permitAll()

            .successHandler((request,response,authentication)->{
          
            Collection<? extends GrantedAuthority> authority =  authentication.getAuthorities();

            for(GrantedAuthority authority2:authority){
                String role = authority2.getAuthority();

                if(role.equals("ROLE_ADMIN")){
                    response.sendRedirect("/admins.html");
                    return;
                }
                else if(role.equals("ROLE_USER")){
                    response.sendRedirect("/dashboard.html");
                    return;
                }
            }

            response.sendRedirect("/index");

         })

         )
      .build();
    }

    
}
