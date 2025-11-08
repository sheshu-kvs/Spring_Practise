package com.demo.foodwebapplication.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.foodwebapplication.Repository.userRepo;
import com.demo.foodwebapplication.Service.userService;
import com.demo.foodwebapplication.model.user;

@Service
public class userServiceImpl implements  userService {

    @Autowired
    public userRepo userRepo;

    @Override
    public user saveUser(user u) {
        return  userRepo.save(u);
    }

    @Override
    public List<user> getAll() {
        return userRepo.findAll();
    }

    @Override
    public user getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

   
    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

            @Override
            public user loginUser(String name, String password) {
            Optional<user> userOpt = userRepo.findByName(name);

            if(userOpt.isEmpty()){
            throw new ResourceNotFoundException("User Not Found");
            }

            user userobj=userOpt.get();
            if(userobj.getPassword().equals(password)){
            return userobj;
            }
            else{
            throw new ResourceNotFoundException("Invalid user");
            }

            }
}
