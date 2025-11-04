package com.demo.foodwebapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class cart_item {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private long cart_item_id;
    
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private cart cart; // Each item belongs to one cart

     @ManyToOne
    @JoinColumn(name = "food_id")
    private food_item food; // Each item refers to one food item
    private int quantity;

    public cart_item() {
    }

    public cart_item(long cart_item_id, com.demo.foodwebapplication.model.cart cart, food_item food, int quantity) {
        this.cart_item_id = cart_item_id;
        this.cart = cart;
        this.food = food;
        this.quantity = quantity;
    }

    public long getCart_item_id() {
        return cart_item_id;
    }

    public void setCart_item_id(long cart_item_id) {
        this.cart_item_id = cart_item_id;
    }

    public cart getCart() {
        return cart;
    }

    public void setCart(cart cart) {
        this.cart = cart;
    }

    public food_item getFood() {
        return food;
    }

    public void setFood(food_item food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    



    

    
    
}
