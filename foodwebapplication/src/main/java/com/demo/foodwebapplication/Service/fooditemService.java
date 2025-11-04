package com.demo.foodwebapplication.Service;

import java.util.List;

import com.demo.foodwebapplication.model.food_item;

public interface fooditemService {
    food_item saveRestaurant(food_item f);
     List<food_item> getAll();
     food_item getFoodById(Long id);
     void deleteUser(Long id); 
    
    
}
