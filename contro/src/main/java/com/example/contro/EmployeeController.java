package com.example.contro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;



@RestController
@RequestMapping("/emp")  //Base endpoint for all the controllers..

public class EmployeeController {

    ArrayList<Employee> emp=new ArrayList<>();
    long currentId=1;

    public EmployeeController(){
        emp.add(new Employee(currentId++, "Alex", "IT", 45089.23));
        emp.add(new Employee(currentId++, "Elon", "IT", 24506.34));
        emp.add(new Employee(currentId++, "James", "IT", 234223.45));
        emp.add(new Employee(currentId++, "Helvi", "IT", 45089.23));
        emp.add(new Employee(currentId++, "Rubbi", "IT", 8978.23));
        emp.add(new Employee(currentId++, "Dom", "IT", 78232.23));
        emp.add(new Employee(currentId++, "Deriva", "IT", 23234.23));
        emp.add(new Employee(currentId++, "Eynim", "IT", 47645.34));
        emp.add(new Employee(currentId++, "Angel", "IT", 45089.23));
        emp.add(new Employee(currentId++, "werins", "IT", 8978.34));
        emp.add(new Employee(currentId++, "werins", "IT", 8978.34));
        emp.add(new Employee(currentId++, "Jeevan", "HR", 90233.23));
        
    }

    // {
    //     {"name":"werins123","role":"IT1","salary":98000}
    //     {"name":"werins123","role":"IT1","salary":98000}
    //   }

 
    
// Creating the New Emp
    @PostMapping("/add")
    public Employee creatEmp(@RequestBody Employee e){
        e.setId(currentId++);
        emp.add(e);
      return e;
    }

    // view all the Employees
    @GetMapping("/view")
    public List<Employee> getALLEmp(){
      return emp;
    }

// Get the Element by ID
    @GetMapping("/{id}")
    public Employee getEmpByID(@PathVariable long id){
      for(Employee e123:emp){
        if(e123.getId()==id){
          return e123;
        }
      }
      throw new RuntimeException("Hey emp entered id is not present");
    }
// Delete The Emp By Id
    @DeleteMapping("/{id}")
    public String deleteEmpId(@PathVariable long id){
      for(int i=0;i<emp.size();i++){
        if(emp.get(i).getId()==id){
          emp.remove(id);
          return"Entered Id is Successsfully delted";
        }
      }
      return"Emp not found "+id;
    }
// Delete All The Employees
    @DeleteMapping("/dell")
    public void deleteEmp(){
       emp.clear();
       System.out.println("Successfully Deleted All The Employee!!");   
      
    }
  


    @PutMapping("/{id}")

    public Employee UpdateEmp(@PathVariable long id,@RequestBody Employee empdetails){
         Employee e=getEmpByID(id);
        e.setName(empdetails.getName());
        e.setRole(empdetails.getRole());
        e.setSalary(empdetails.getSalary());
        return e;
    }
}
