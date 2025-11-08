package com.demo.foodwebapplication.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.demo.foodwebapplication.model.food_item;

public interface fooditemService {
     food_item saveFood(food_item f);
     food_item updateFood(food_item f);
     List<food_item> getAll();
     food_item getFoodById(Long id);
     void deleteFoodById(Long id); 
     List<food_item> getFoodsByRestaurantId(long restaurantId);
    String saveFoodImage(long id, MultipartFile file) throws IOException;


    
    
}
