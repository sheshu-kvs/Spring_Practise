package com.example.springintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringintroApplication {

	public static void main(String[] args) {
		// ConfigurableApplicationContext
		ConfigurableApplicationContext context = SpringApplication.run(SpringintroApplication.class, args);


		studentrepo sr=context.getBean(studentrepo.class);
		studentservice ss=context.getBean(studentservice.class);

		String resOfStudentRepo = sr.dispstudentRepo();
		String resOfstudentService=ss.dispService();

		System.out.println("Result "+resOfStudentRepo);
		System.out.println("Result "+resOfstudentService);
	}

}
