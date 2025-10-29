package com.example.springregistraion.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springregistraion.model.MyAppuser;
import com.example.springregistraion.repo.MyAppRepo;

import jakarta.transaction.Transactional;

@Service
// this interface reteriving the userdetails for the authetication
public class MyAppuserService implements UserDetailsService {


    private MyAppRepo repo;
    
    public MyAppuserService(MyAppRepo repo){
        this.repo=repo;
    }
    


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // It return the user
        Optional<MyAppuser> user=repo.findByUsername(username);

        if(user.isPresent()){
            var userObj = user.get();
            return User.builder()
             .username(userObj.getUsername())
             .password(userObj.getPassword())
             .roles(userObj.getRole())
             .build();
        }
        else{
            throw new  UsernameNotFoundException(username);
        }

    }
// “I tried X, I’m seeing this error, I expected Y. What am I missing?”


    @Transactional
    public MyAppuser saveNewUser(MyAppuser myuser){
        return repo.save(myuser);
    }


    public MyAppuser findbyusername(String username){
        Optional<MyAppuser> user = repo.findByUsername(username);
        return user.orElse(null);
    }

  
  
    
}
