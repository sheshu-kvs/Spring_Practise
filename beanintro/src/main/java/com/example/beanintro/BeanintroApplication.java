package com.example.beanintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BeanintroApplication {

	public static void main(String[] args) {


    ConfigurableApplicationContext context = SpringApplication.run(BeanintroApplication.class, args);

	studentname stud= (studentname) context.getBean(studentname.class);

	String Result = stud.displayingStudentName();
	System.out.println("Result: "+Result);

	System.out.println("Hello How Are U !!!");

	}
}

/*Spring Core  Concepts that is IOC(Inversion of the Controll):::
 * in the Java if we want to create the object we can use the we ar e using the new keyword.
 * in the spring to avoid this spring will automatically creates the object..
 * 
 * 
 * Dependency Injection:::
 * in the spring how the IOC is implemented
 * 
 * there 3 ways to achieve the dependencie injection
 * ->Constructor injection.
 * ->Field injection.
 * ->Setter Injection.
 * 
 * 
 * Example of the Constructor Injection
 * 
 * public class studentInfo{}
 * 
 * public class StudentDisp{
 * 
 * private final StudentInfo studentinfo;
 * 
 * //Constructor Injection..
 * public StudentDisp(StudentInfo studentinfo){
 * this.studentinfo = studentinfo;
 * }
 * }
 * 
 * Setter Injection
 * 
 * public class studentInfo{
 * }
 * 
 * public class studentDisp{
 * 
 * private final StudentInfo info;
 * 
 * public setId(Studentinfo info){
 * this.info = info;
 * }
 * }
 * 
 * Field Injection..
 * 
 * public class studentInfo{
 * 
 * }
 * 
 * public class StudentDisp{
 * @Autowired 
 * private studentInfo studentinfo;
 * 
 * }
 * 
 * 
 * 
 */
