package com.demo.foodwebapplication.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.foodwebapplication.Repository.food_itemRepo;
import com.demo.foodwebapplication.Service.fooditemService;
import com.demo.foodwebapplication.model.food_item;

public class fooditemServiceImpl implements fooditemService{
    @Autowired
    public food_itemRepo foodItemRepo;

    @Override
    public food_item saveRestaurant(food_item f) {
        return foodItemRepo.save(f);
    }

    @Override
    public List<food_item> getAll() {
        return foodItemRepo.findAll();
    }

    @Override
    public food_item getFoodById(Long id) {
        return foodItemRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        foodItemRepo.deleteById(id);
    }
    
}
