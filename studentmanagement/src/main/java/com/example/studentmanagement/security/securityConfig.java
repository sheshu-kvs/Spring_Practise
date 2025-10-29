package com.example.studentmanagement.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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

    /*
     * | Step | Who Handles It                | Description                                              |
| ---- | ----------------------------- | -------------------------------------------------------- |
| 1    | **Browser (HTML Form)**       | User fills the form → `POST /login`                      |
| 2    | **Spring Security Filter**    | Intercepts `/login`, reads `username` and `password`     |
| 3    | **AuthenticationManager**     | Calls your `UserDetailsService.loadUserByUsername()`     |
| 4    | **UserDetailsService**        | Finds user in DB, returns `UserDetails` object           |
| 5    | **PasswordEncoder**           | Verifies the entered password with stored hash           |
| 6    | **SuccessHandler / Redirect** | Redirects user based on role                             |
| 7    | **Controller**                | Finally renders correct page (`user.html`, `admin.html`) |




| Concept                            | Why It Matters                        |
| ---------------------------------- | ------------------------------------- |
| Form action `/login` (POST)        | Connects your HTML to Spring Security |
| Field names `username`, `password` | Required for Spring to read values    |
| `@GetMapping("/login")`            | Shows login page                      |
| `UserDetailsService`               | Fetches user from DB                  |
| `PasswordEncoder`                  | Validates passwords                   |
| Role names (`ROLE_ADMIN`)          | Determines page redirection           |
| SuccessHandler                     | Controls where user goes after login  |
| Debug Logs                         | Show exactly what went wrong          |



     */
    
// @Autowired
// private UserDetailsService userdetailservice;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    } 

    @Bean
    public AuthenticationProvider authenticationProvider(studentservice service){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
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
            // .defaultSuccessUrl("/index",true)

            .successHandler((request,response,authentication)->{
            Collection <? extends GrantedAuthority> authority=authentication.getAuthorities();
            for(GrantedAuthority authority2:authority){
                String role=authority2.getAuthority();
                System.out.println("Role "+role);
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
