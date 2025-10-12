package com.sheshu.springjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringjdbcApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(SpringjdbcApplication.class, args);


		car c=context.getBean(car.class);

		String res=c.drive();
		

		
		System.out.println(res);
		System.out.println("Hello");
	}

}
