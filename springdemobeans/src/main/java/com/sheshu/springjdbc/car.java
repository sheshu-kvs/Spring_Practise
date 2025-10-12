package com.sheshu.springjdbc;

import org.springframework.stereotype.Component;

// @Component //this class is the bean class
public class car {
 private final engine eng;

    public car(engine eng){
    this.eng=eng;
    }


    public String drive(){
        return eng.start()+" car is driving";
    }
}
