// package com.example.service;

// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.example.model.Employee;
// import com.example.repository.EmployeeRepo;

// // In service/EmployeeService.java, create:

// // @Service

// // Inject EmployeeRepository via constructor

// // Methods (implement logic + simple checks):

// // public Employee create(Employee e)

// // ensure name not null/blank

// // call repo.save(e)

// // public List<Employee> getAll()

// // public Employee getById(Long id)

// // if not found → throw a custom exception (next step)

// // public Employee update(Long id, Employee e)

// // ensure exists, update fields, save

// // public void delete(Long id)
// @Service
// public class EmployeeService {
//     private EmployeeRepo employeerepo;



//     public Employee create(Employee e){
//         return employeerepo.save(e);
//     }

//     public List<Employee> getAll(){
//         return employeerepo.findAll();
//     }

//     public Employee getById(int id){
//         return (Employee) employeerepo.findById();
//     }
//     // public Employee update(Long id,Employee e){
//     //     return employeerepo.
//     // }
//     // public void delete(Long id){
//     //     employeerepo.deleteById(id);
//     // }
  

    


// }
