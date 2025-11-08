package com.demo.foodwebapplication.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.demo.foodwebapplication.model.user;


@Service
public interface userService {
    user saveUser(user u);
    user loginUser(String name,String password);
     List<user> getAll();
     user getUserById(Long id);
    void deleteUser(Long id); 


  


  


  

}
