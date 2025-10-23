package com.example.springcontrollerexample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
/*this Controller anootation are used to the MVC(model view controll )in the spring
 * 
 */
public class indexController{


    @GetMapping("/home")
    public  String getData(){
        return "index";
    }

    /*so in the server when we run the http://localhost:8080//home 
     * this url directly redirects from the templates to the spring(i,e index.html file).
     * here htmlfiles or the thymleaf are called as the: views  
     * model means java classes , controller means the controller classes in the spring
    */
    
}