package com.demo.foodwebapplication.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.foodwebapplication.Repository.cartItemRepo;
import com.demo.foodwebapplication.Repository.cartRepo;
import com.demo.foodwebapplication.Repository.ordersRepo;
import com.demo.foodwebapplication.Service.cartService;
import com.demo.foodwebapplication.Service.orderService;
import com.demo.foodwebapplication.model.cart;
import com.demo.foodwebapplication.model.cart_item;
import com.demo.foodwebapplication.model.food_item;
import com.demo.foodwebapplication.model.orders;
import com.demo.foodwebapplication.model.user;


@Service
public class orderServiceImpl implements orderService {

    @Autowired
    private cartService cartService;

    @Autowired
    private ordersRepo orderRepo;

    @Autowired
    private cartRepo cartRepo;

    @Autowired
    private cartItemRepo cartItemRepo;
    


    
    @Override
    public orders placeOrder(user user) {
        // Step 1: Get user cart
        cart cart = cartRepo.findByUser(user).orElse(null);
        if (cart == null) {
            throw new RuntimeException("Cart not found for user: " + user.getUser_id());
        }

        // Step 2: Create new order
        orders order = new orders();
        order.setUser(user);
        order.setTotal_price(cart.getTotal_price());
        order.setStatus("PLACED"); // default status
        orderRepo.save(order);

        // Step 3: Copy cart items into the order
        List<cart_item> cartItems = cartItemRepo.findByCart(cart);
        for (cart_item item : cartItems) {
            food_item food = item.getFood();
            // You can add OrderItem creation logic if you have OrderItem entity
        }

        // Step 4: Clear cart after placing order
        cartService.clearCart(user);

        return order;
    }

    @Override
    public List<orders> getOrdersByUser(user user) {
        return orderRepo.findByUser(user);
    }

    @Override
    public orders getOrderById(Long orderId) {
        return orderRepo.findById(orderId).orElse(null);
    }

    @Override
    public List<orders> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public void cancelOrder(Long orderId) {
        orders order = orderRepo.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus("CANCELLED");
            orderRepo.save(order);
        }
    }

}
