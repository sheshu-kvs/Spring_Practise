package com.demo.foodwebapplication.model;

public class cartRequest {
    private long user_id;
    private long food_id;
    private int quantity;
    public cartRequest() {
    }
    public cartRequest(long user_id, long food_id, int quantity) {
        this.user_id = user_id;
        this.food_id = food_id;
        this.quantity = quantity;
    }
    public long getUser_id() {
        return user_id;
    }
    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
    public long getFood_id() {
        return food_id;
    }
    public void setFood_id(long food_id) {
        this.food_id = food_id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    



    
}
