package com.demo.foodwebapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_item", // You might already have a name, if not, add it
       indexes = {
           @Index(name = "idx_restaurant_id", columnList = "restaurantId") // 👈 THIS IS THE CHANGE
       })
       //before 834.08 ms to load the data in the each restaurant with the food_item.. now it is taking 781.35 ms
       //before cacheable it wolud taking the 781.35ms 
public class food_item {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long food_id;
    private String name;
    private float price;
    private String description;
    private long restaurantId;
    private String imagepath;



    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public food_item() {
    }

    public food_item(String description, long food_id, String imagepath, String name, float price, long restaurantId) {
        this.description = description;
        this.food_id = food_id;
        this.imagepath = imagepath;
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    







    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
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

 


    
}
