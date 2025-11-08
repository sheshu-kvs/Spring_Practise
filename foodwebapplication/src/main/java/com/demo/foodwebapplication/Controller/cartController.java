package com.demo.foodwebapplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.foodwebapplication.Repository.food_itemRepo;
import com.demo.foodwebapplication.Repository.userRepo;
import com.demo.foodwebapplication.Service.cartService;
import com.demo.foodwebapplication.model.cart;
import com.demo.foodwebapplication.model.cartRequest;
import com.demo.foodwebapplication.model.cart_item;
import com.demo.foodwebapplication.model.food_item;
import com.demo.foodwebapplication.model.user;
@RestController
@RequestMapping("/cart")
public class cartController {

    @Autowired
    private cartService cartService;

    @Autowired
    private userRepo userRepo;

    @Autowired
    private food_itemRepo foodRepo;


    // ✅ 1️⃣ Get all cart items by user ID
    @GetMapping("/{user_id}")
    public ResponseEntity<?> getCartItemsByUser(@PathVariable long user_id) {
        List<cart_item> items = cartService.getCartItemsByUserId(user_id);  

        if (items.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("No items found in user's cart");
        }

        return ResponseEntity.ok(items);
    }


    // ✅ 2️⃣ Add a food item to a user's cart
    @PostMapping("/add")
    public ResponseEntity<?> addItemToCart(@RequestBody cartRequest cartRequest) {
        // 1. Find user
        user u = userRepo.findById(cartRequest.getUser_id()).orElse(null);
       
        if(u==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Was Not Found..");
        }

        // 2. Find food item
        food_item food = foodRepo.findById(cartRequest.getFood_id() )
                .orElseThrow(() -> new RuntimeException("Food item not found"));

        // 3. Add to cart
        cart updatedCart = cartService.addItemToCart(u, food, cartRequest.getQuantity());

        return ResponseEntity.ok(updatedCart);
    }


    // ✅ 3️⃣ Remove an item from a user's cart
    @DeleteMapping("/remove")
    public ResponseEntity<?> removeItemFromCart(@RequestParam long userId,
                                                @RequestParam long cartItemId) {
        user u = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        cartService.removeItemFromCart(u, cartItemId);
        return ResponseEntity.ok("Item removed successfully");
    }


    // ✅ 4️⃣ Get all items from a user’s active cart
    @GetMapping("/items/{userId}")
    public ResponseEntity<?> getUserCartItems(@PathVariable long userId) {
        user u = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<cart_item> items = cartService.getCartItems(u);
        return ResponseEntity.ok(items);
    }


    // ✅ 5️⃣ Clear the user's cart (e.g., after order placement)
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<?> clearCart(@PathVariable long userId) {
        user u = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        cartService.clearCart(u);
        return ResponseEntity.ok("Cart cleared successfully");
    }
}
