package com.demo.foodwebapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity

public class restaurant {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long restaurant_id;
    private String name;
    private String location;
    private float rating;

    public restaurant() {
    }

    public restaurant(String location, String name, float rating, long restaurant_id) {
        this.location = location;
        this.name = name;
        this.rating = rating;
        this.restaurant_id = restaurant_id;
    }

    public long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(long restaurant_id) {
        this.restaurant_id = restaurant_id;
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
