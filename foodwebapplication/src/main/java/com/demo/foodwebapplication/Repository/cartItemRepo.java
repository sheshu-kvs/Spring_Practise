package com.demo.foodwebapplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.foodwebapplication.model.cart;
import com.demo.foodwebapplication.model.cart_item;

public interface cartItemRepo extends JpaRepository<cart_item, Long> {
         List<cart_item> findByCart(cart cart);
    
}
