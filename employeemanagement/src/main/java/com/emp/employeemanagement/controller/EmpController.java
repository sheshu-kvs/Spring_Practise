package com.emp.employeemanagement.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.emp.employeemanagement.model.Emp;
import com.emp.employeemanagement.service.EmpService;


@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
@RequestMapping("/emp")  // base endpoints for all the http method requests
public class EmpController {

    private EmpService empservice;

    public EmpController(EmpService empservice){
        this.empservice=empservice;
    }

    @GetMapping
    public List<Emp> getAllEmp(){
        return empservice.getAllEmp();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpById(@PathVariable Long id){
         Emp emp=empservice.getEmpByid(id);
         if(emp == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter The Id Was Not Found.. "+id);
         }
         return ResponseEntity.ok(emp);
    }

    // Adding the Emp Details 

    @PostMapping
    public Emp AddEmp(@RequestBody Emp emp){
        Emp SavedEmp = empservice.AddEmpDetails(emp);
        return SavedEmp;
    } 

    @PutMapping("/{id}")
    public Emp updateEmp(@PathVariable Long id, @RequestBody Emp emp){
        // this setId we are using because it need to set the particular empid it can be updated
        emp.setId(id);
        Emp UpdateEmp = empservice.UpdateEmp(emp);
        return UpdateEmp;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpById(@PathVariable Long id){
        boolean isdeleted = empservice.DeleteEmpById(id);
        if(isdeleted){
            return ResponseEntity.status(HttpStatus.FOUND).body("Entered Employee Successfuly Deleted "+id);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entered Employee Was Not Found "+id);
        }
    }
}
