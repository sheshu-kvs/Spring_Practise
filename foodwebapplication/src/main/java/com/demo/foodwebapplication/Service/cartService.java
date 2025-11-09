package com.demo.foodwebapplication.Service;

import java.util.List;

import com.demo.foodwebapplication.model.cart;
import com.demo.foodwebapplication.model.cart_item;
import com.demo.foodwebapplication.model.food_item;
import com.demo.foodwebapplication.model.user;

public interface cartService {

    // Get a user's active cart
    cart getCartByUser(user user);
    
    List<cart_item> getCartItemsByUserId(long userId);

    // Add a food item to user's cart
    cart addItemToCart(user user, food_item food, int quantity);

    // Remove a particular item from user's cart
    void removeItemFromCart(user user, long cart_item_id);

    // Get all items in user's cart
    List<cart_item> getCartItems(user user);

    // Clear all items (after order placed)
    void clearCart(user user);

}
