package com.demo.loginvalidation.model;

public class user {
    private long id;
    private String name;
    private String email;
    private String phonenumber;

    public user() {
    }

    public user(long id, String name, String email, String phonenumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public long getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "user [id=" + id + ", name=" + name + ", email=" + email + ", phonenumber=" + phonenumber + "]";
    }

}