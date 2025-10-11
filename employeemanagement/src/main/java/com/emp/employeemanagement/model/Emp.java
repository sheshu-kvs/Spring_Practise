package com.emp.employeemanagement.model;

public class Emp {
    private long id;
    private String name;
    private Float salary;

    
    public Emp() {
    }


    public Emp(long id, String name, Float salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
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


    public Float getSalary() {
        return salary;
    }


    public void setSalary(Float salary) {
        this.salary = salary;
    }

    
}
