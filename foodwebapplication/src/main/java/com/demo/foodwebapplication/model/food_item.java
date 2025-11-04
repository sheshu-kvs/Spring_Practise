package com.demo.foodwebapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class food_item {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long food_id;
    private String name;
    private float price;
    private String description;
    private long restaurant_id;

    public food_item() {
    }

    public food_item(String description, long food_id, String name, float price, long restaurant_id) {
        this.description = description;
        this.food_id = food_id;
        this.name = name;
        this.price = price;
        this.restaurant_id = restaurant_id;
    }

    public long getFood_id() {
        return food_id;
    }

    public void setFood_id(long food_id) {
        this.food_id = food_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }


    
}
