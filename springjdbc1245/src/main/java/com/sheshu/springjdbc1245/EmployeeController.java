package com.sheshu.springjdbc1245;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepo emprepo;

    @GetMapping
    public List<Employee> getallEmp(){
        return emprepo.findall();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpById(@PathVariable Long id){
        try {
            Employee emp = emprepo.findById(id);
            return ResponseEntity.ok(emp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entered user was not present");
        }
    }

    // // ADDED: Create new employee
    // @PostMapping
    // public Employee createEmployee(@RequestBody Employee emp) {
    //     return emprepo.save(emp);
    // }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee emp) {
        try {
            emp.setId(id);
            Employee updatedEmp = emprepo.updateEmp(emp);
            return ResponseEntity.ok(updatedEmp);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            emprepo.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}