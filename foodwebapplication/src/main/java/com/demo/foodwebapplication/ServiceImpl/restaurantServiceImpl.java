package com.demo.foodwebapplication.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.foodwebapplication.Repository.restaurantRepo;
import com.demo.foodwebapplication.Service.restaurantService;
import com.demo.foodwebapplication.model.restaurant;


@Service
public class restaurantServiceImpl implements restaurantService {

    @Autowired
    public restaurantRepo restaurantRepo;


    @Override
    public restaurant saveRestaurant(restaurant r) {
        return restaurantRepo.save(r);
    }

    @Override
    public List<restaurant> getAll() {
        return restaurantRepo.findAll();
    }

    @Override
    public restaurant getRestaurantById(Long id) {
        return restaurantRepo.findById(id).orElse(null);
    }


    @Override
    public void deleteUser(Long id) {
        restaurantRepo.deleteById(id);
    }
    
}
