package com.demo.foodwebapplication.Service;

import com.demo.foodwebapplication.model.*;
import java.util.List;

public interface cartService {

    // Get a user's active cart
    cart getCartByUser(user user);

    // Add a food item to user's cart
    cart addItemToCart(user user, food_item food, int quantity);

    // Remove a particular item from user's cart
    void removeItemFromCart(user user, long cart_item_id);

    // Get all items in user's cart
    List<cart_item> getCartItems(user user);

    // Clear all items (after order placed)
    void clearCart(user user);
}
