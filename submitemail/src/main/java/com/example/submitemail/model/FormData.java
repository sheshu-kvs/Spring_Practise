package com.example.submitemail.model;

public class FormData {
    private String name;
    private String address;
    private String message;

   
    public FormData() {}

   
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }
}
