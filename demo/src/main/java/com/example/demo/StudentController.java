package com.example.demo;

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

import com.example.model.Student;

@RestController
@RequestMapping("/stu")
public class StudentController {
    long id123=1;


    // HashMap<Integer,Student> map=new HashMap<>();
    ArrayList<Student> arr1=new ArrayList<>();

    public StudentController(){
        //    arr1.add(id123++,new Student( "Alex", "UK"));
        //    arr1.add(id123++,new Student( "Alex", "UK"));

        arr1.add(new Student(id123++, "Alex", "Uk"));
        arr1.add(new Student(id123++, "Sammy", "Europe"));
        arr1.add(new Student(id123++, "Zeniva", "London"));
    }



    @GetMapping("/")
     public List<Student> getall(){
        return arr1;
     }

     @GetMapping("/{id}")
     public String getEmpById(@PathVariable long id){
        for(int i=0;i<arr1.size();i++){
            if(arr1.get(i).getId()==id){
                return"Student Found"+arr1.get(i);
            }
        }
        return"Student Not Found";

     }



     @PostMapping("/")
     public Student addStu(@RequestBody Student s1){
        s1.setId(id123++);
        arr1.add(s1);
        return s1;
     }

     @DeleteMapping("/")
     public void deleteStu(){
        arr1.clear();
        System.out.println("All the Employees Deleted Successfully!!");

     }
     @DeleteMapping("/{id}")
     public String deletstuId(@PathVariable long id){
        for(int i=0;i<arr1.size();i++){
            if(arr1.get(i).getId()==id){
                arr1.remove(id);
              return "Entered Id Succesfully Deleted";
                
            }
        }
        return"Entered Id Was Not Present..";
     }


     @PutMapping("/{id}")

     public void updateStu(@PathVariable long id,@RequestBody Student s1){
        // get
        //   Student s1=getEmpById(id);
             for(int i=0;i<arr1.size();i++){
            if(arr1.get(i).getId()==id){
                s1.setName(s1.getName());
                s1.setAddress(s1.getAddress());
                System.out.println(s1);
            }
        }
     }

     

   







    
}
