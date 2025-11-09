package com.demo.foodwebapplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.foodwebapplication.Service.orderService;
import com.demo.foodwebapplication.Service.userService;
import com.demo.foodwebapplication.model.orders;
import com.demo.foodwebapplication.model.user;

@RestController
@RequestMapping("/order")
public class orderController{

    @Autowired
    private orderService orderService;

    @Autowired
    private userService userService; // to fetch user by id

      // ✅ 1️⃣ Place a new order for a user (moves all cart items into an order)
    @PostMapping("/place/{user_id}")
    public ResponseEntity<?> placeOrder(@PathVariable long user_id) {
        try {
            user userObj = userService.getUserById(user_id);
            if (userObj == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            orders newOrder = orderService.placeOrder(userObj);
            return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while placing order: " + e.getMessage());
        }
    }

    // ✅ 2️⃣ Get all orders for a particular user
    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getOrdersByUser(@PathVariable long user_id) {
        try {
            user userObj = userService.getUserById(user_id);
            if (userObj == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            List<orders> orderList = orderService.getOrdersByUser(userObj);
            return ResponseEntity.ok(orderList);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while fetching user orders: " + e.getMessage());
        }
    }

    // ✅ 3️⃣ Get all orders (Admin view)
    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders() {
        try {
            List<orders> allOrders = orderService.getAllOrders();
            return ResponseEntity.ok(allOrders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while fetching all orders: " + e.getMessage());
        }
    }

    // ✅ 4️⃣ Get a specific order by ID
    @GetMapping("/{order_id}")
    public ResponseEntity<?> getOrderById(@PathVariable long order_id) {
        try {
            orders order = orderService.getOrderById(order_id);
            if (order == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
            }
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching order details: " + e.getMessage());
        }
    }

    // ✅ 5️⃣ Cancel an order by ID
    @DeleteMapping("/cancel/{order_id}")
    public ResponseEntity<?> cancelOrder(@PathVariable long order_id) {
        try {
            orderService.cancelOrder(order_id);
            return ResponseEntity.ok("Order cancelled successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error cancelling order: " + e.getMessage());
        }
    }
    
}