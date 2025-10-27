package com.example.springregistraion.security;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    // this service class are used to load the db detils in this class



    @Bean
    //  @Bean this class will return the object and storing in the spring container..
    public UserDetailsService userDetailsService(MyAppuserService userservice){
        return userservice;
        // as of now this UserDetailsService load the userservice class when  the spring needs it
        // will use this bean.. 
    }
    // 

    @Bean
    // this is the another bean here the actual authentication will happen 
    // in this we are passing the userDetailsServic actualthe userservice cls
    public AuthenticationProvider AuthenticationProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        // this DaoAuthenticationProvide uses a db to authenticate userservice
       
        provider.setPasswordEncoder(passwordEncoder());
        // here it is comparing the when the user logins :login pwd and the ecoded pwd

        return provider;
    }   


    @Bean

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        // this bean are the responsible to the make the password to the hash
    }

    



    @Bean
    // we need to define our security roles
    public  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
          return httpSecurity
        // it avoids the cyber security attacks.
        // it is not recomded for the production.

        .csrf(AbstractHttpConfigurer::disable)
        // Cross Site Request Forgery Protection...
        // spring security will automaticallly blocks the posts requests in the spring 
        // we have disabled it..


        // this controlles who can acces that 
         .authorizeHttpRequests(register->{
                // specifying the url for the registration page..
               // Everyone can open the registation page...
                register.requestMatchers("/req/**").permitAll();
                register.requestMatchers("/admin/**").hasRole("ADMIN");   //only the admin can 
                register.requestMatchers("/user/**").hasAnyRole("USER","ADMIN"); //anyone of the role they can login..

                register.requestMatchers("/forgotpwd","/req/updatepwd").permitAll();

        
                //everthing requires the authentication..
                register.anyRequest().authenticated();
            })



        // how user can login our webapge
            .formLogin(httpForm ->{
                httpForm.loginPage("/login").permitAll();
                //after user login the page means it will redirect to this page
                // After the succesfull login user can redirect to the index page
                // spring calls the userdetails service if the entered matches means 
                // it will redirect to the index page.. 
                // httpForm.defaultSuccessUrl("/index",true);
                httpForm.successHandler((request,response,authentication)->{
                    Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();

                    for(GrantedAuthority authority:authorities){
                        String role = authority.getAuthority();
                        
                        if(role.equals("ROLE_ADMIN")){
                            response.sendRedirect("/admin");
                            return;
                        }
                        else if(role.equals("ROLE_USER")){
                            response.sendRedirect("/index");
                            return;
                        }
                    }
                    response.sendRedirect("/index");
                });
              })
            //   we need to use the registration page without the authentication..

           



              .build();
    }
    
}
