package com.example.springdependencyinjection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringdependencyinjectionApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringdependencyinjectionApplication.class, args);

		Car c = context.getBean(Car.class);
		String res = c.disp();
		System.out.println(res);
		
	}

}
