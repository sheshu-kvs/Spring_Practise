// package com.example.repository;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;

// import org.springframework.stereotype.Repository;

// import com.example.model.Employee;
// @Repository
// public class EmployeeRepo {
//    private static Map<Integer,Employee> map=new HashMap<>();
//     int Id=1;

//     // public Employee save(Employee e){
//     //     // if the Id is Null it saving the saving the data..
//     //     map.put(e.getId(),e);

//     //     return e;
//     // }
//     public List<Employee> findAll(){
//         // here we are stroing the values in the list
//         return new ArrayList<>(map.values());        
//     }

//     // public Employee findById(){
//     //     Employee e1= map.get("Id");
//     //     return Optional.ofNullable(e1);

//     }

//     public void deleteById(int Id){
//         map.remove("Id");
//     }

    


// // }
