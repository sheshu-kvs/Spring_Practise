package com.demo.foodwebapplication.Service;

import java.util.List;

import com.demo.foodwebapplication.model.orders;
import com.demo.foodwebapplication.model.user;

public interface orderService {

    //Place an order for a user (from their cart)
    orders placeOrder(user user);

    // Get all orders for a specific user
    List<orders> getOrdersByUser(user user);

    // Get details of a specific order
    orders getOrderById(Long orderId);

    // Get all orders (for admin)
    List<orders> getAllOrders();

    // Cancel a specific order
    void cancelOrder(Long orderId);
    
}
