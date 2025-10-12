package com.sheshu.springjdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration //tells this class provide the bean definition
public class Appconfig {

    @Bean
   public engine engine(){
    return new engine();
   }

   @Bean
   public car car(){
    return new car(engine());
   }
    
}
