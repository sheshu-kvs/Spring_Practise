package com.example.studentprac.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class student{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)


    private long id;
    private String name;
    private String qualification;
    private String email;
    private String role;
   


    private String address;
    private String password;
    private String filepath;

    
    public student() {
    }


    


    
    


    public student(long id, String name, String qualification, String email, String role, String address,
            String password, String filepath) {
        this.id = id;
        this.name = name;
        this.qualification = qualification;
        this.email = email;
        this.role = role;
        this.address = address;
        this.password = password;
        this.filepath = filepath;
    }









    public long getId() {
        return id;
    }

     public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getQualification() {
        return qualification;
    }


    public void setQualification(String qualification) {
        this.qualification = qualification;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getFilepath() {
        return filepath;
    }


    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    
}