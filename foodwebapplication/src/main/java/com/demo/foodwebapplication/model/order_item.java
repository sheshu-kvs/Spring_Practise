package com.demo.foodwebapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class order_item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_item_id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private orders order; // each item belongs to one order

    @ManyToOne
    @JoinColumn(name = "food_id")
    private food_item food; // each order item points to one food

    private int quantity;

    public order_item() {}

    public long getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(long order_item_id) {
        this.order_item_id = order_item_id;
    }

    public orders getOrder() {
        return order;
    }

    public void setOrder(orders order) {
        this.order = order;
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