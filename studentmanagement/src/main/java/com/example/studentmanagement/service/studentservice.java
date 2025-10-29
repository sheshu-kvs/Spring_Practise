package com.example.studentmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.studentmanagement.model.student;
import com.example.studentmanagement.repository.studentrepo;

import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class studentservice implements UserDetailsService {

    @Autowired
    private studentrepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public student save(student stu) {
        stu.setPassword(passwordEncoder.encode(stu.getPassword()));
        return repo.save(stu);
    }

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<student> stu = repo.findByName(name);
        if (stu.isEmpty()) {
            throw new UsernameNotFoundException(name);
        }
        var stuObj = stu.get();
        String role = stuObj.getRole(); // this role values are getting from the db...
        if (role == null || role.isBlank()) {
            role = "USER";
        }
        return User.builder()  //convert to spring security userdetails...   here converting the java object to the spring security object..
                .username(stuObj.getName())  //username for the authentication 
                .password(stuObj.getPassword()) //password for the matching...
                .roles(role)  // getting the role according to the role it need to redirect..
                .build();
    }

}
