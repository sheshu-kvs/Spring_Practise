package com.example.springwithjpa.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class product {

    @Id

    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Long id;
    private String productname;
    private float price;

    
    public product() {
    }


    public product(Long id, String productname, float price) {
        this.id = id;
        this.productname = productname;
        this.price = price;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getProductname() {
        return productname;
    }


    public void setProductname(String productname) {
        this.productname = productname;
    }


    public float getPrice() {
        return price;
    }


    public void setPrice(float price) {
        this.price = price;
    }

    
}
