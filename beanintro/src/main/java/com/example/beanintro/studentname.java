package com.example.beanintro;

import org.springframework.stereotype.Component;

@Component    
            // tells to spring this is the bean class  
            // which means when the application starts the object will create by the  spring. 
            //  will automatically create the object..  
public class studentname{
    // without  creating the object of the studentinfo class 

    // here we are making the constructor injection,  injecting the studentinfo class to this class
    private  studentInfo studentinfo;

    public studentname(studentInfo studentinfo){
        this.studentinfo = studentinfo;
    }   

    public  String displayingStudentName(){
        return "My Name is the " +studentinfo.disp();
    }


    
}