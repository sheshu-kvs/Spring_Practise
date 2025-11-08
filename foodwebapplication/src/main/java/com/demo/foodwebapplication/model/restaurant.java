package com.demo.foodwebapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// C:\Users\GramaVikasa\Desktop\Internship Projects\Spring_Practise\foodwebapplication\src\main\    

@Entity

public class restaurant {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long restaurantId;
    private String name;
    private String location;
    private float rating;
    private String imagepath;

    public restaurant() {
    }

    

    public restaurant(long restaurantId, String name, String location, float rating, String imagepath) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.imagepath = imagepath;
    }
    



    public String getImagepath() {
        return imagepath;
    }



    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }



    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    



    


    
    
}
