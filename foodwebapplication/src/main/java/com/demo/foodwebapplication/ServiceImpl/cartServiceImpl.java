package com.demo.foodwebapplication.ServiceImpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.foodwebapplication.Repository.cartItemRepo;
import com.demo.foodwebapplication.Repository.cartRepo;
import com.demo.foodwebapplication.Service.cartService;
import com.demo.foodwebapplication.model.cart;
import com.demo.foodwebapplication.model.cart_item;
import com.demo.foodwebapplication.model.food_item;
import com.demo.foodwebapplication.model.user;


@Service
public class cartServiceImpl implements cartService {

    @Autowired
    private cartRepo cartRepo;

    @Autowired
    private cartItemRepo cartItemRepo;

    // 1️⃣ Get a user's active cart
    @Override
    public cart getCartByUser(user user) {
        Optional<cart> optionalCart = cartRepo.findByUser(user);
        cart userCart;

        if (optionalCart.isPresent()) {
            userCart = optionalCart.get();
        } else {
            userCart = new cart();
            userCart.setUser(user);
            userCart.setTotal_price(0);
            cartRepo.save(userCart);
        }

        return userCart;
    }

    // ----------------------------
    // 2. Add item to cart
    // ----------------------------
    @Override
    public cart addItemToCart(user user, food_item food, int quantity) {
        cart userCart = getCartByUser(user);

        // Check if item already exists in cart
        List<cart_item> items = cartItemRepo.findAll();
        cart_item existingItem = null;

        for (cart_item item : items) {
            if (item.getCart().getCart_id() == userCart.getCart_id()
                    && item.getFood().getFood_id() == food.getFood_id()) {
                existingItem = item;
                break;
            }
        }

        if (existingItem != null) {
            // If item exists, increase quantity
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepo.save(existingItem);
        } else {
            // Else, create a new cart item
            cart_item newItem = new cart_item();
            newItem.setCart(userCart);
            newItem.setFood(food);
            newItem.setQuantity(quantity);
            cartItemRepo.save(newItem);
        }

        // Update total price in cart
        float newTotal = userCart.getTotal_price() + (food.getPrice() * quantity);
        userCart.setTotal_price(newTotal);
        cartRepo.save(userCart);

        return userCart;
    }

    // ----------------------------
    // 3. Remove a specific item
    // ----------------------------
    @Override
    public void removeItemFromCart(user user, long cart_item_id) {
        cart userCart = getCartByUser(user);
        Optional<cart_item> optionalItem = cartItemRepo.findById(cart_item_id);

        if (optionalItem.isPresent()) {
            cart_item item = optionalItem.get();
            // Subtract the item's total from cart total
            float itemTotal = item.getQuantity() * item.getFood().getPrice();
            userCart.setTotal_price(userCart.getTotal_price() - itemTotal);
            cartRepo.save(userCart);

            // Remove the item
            cartItemRepo.delete(item);
        }
    }

    // ----------------------------
    // 4. Get all items in the cart
    // ----------------------------
    @Override
    public List<cart_item> getCartItems(user user) {
        cart userCart = getCartByUser(user);
        List<cart_item> allItems = cartItemRepo.findAll();
        java.util.ArrayList<cart_item> userItems = new java.util.ArrayList<>();

        for (cart_item item : allItems) {
            if (item.getCart().getCart_id() == userCart.getCart_id()) {
                userItems.add(item);
            }
        }

        return userItems;
    }

    // ----------------------------
    // 5. Clear all items (after order)
    // ----------------------------
    @Override
    public void clearCart(user user) {
        cart userCart = getCartByUser(user);
        List<cart_item> allItems = cartItemRepo.findAll();

        for (cart_item item : allItems) {
            if (item.getCart().getCart_id() == userCart.getCart_id()) {
                cartItemRepo.delete(item);
            }
        }

        userCart.setTotal_price(0);
        cartRepo.save(userCart);
    }
}
