package com.demo.foodwebapplication.Service;

import java.util.List;

import com.demo.foodwebapplication.model.restaurant;

public interface restaurantService {
     restaurant saveRestaurant(restaurant r);
     List<restaurant> getAll();
     restaurant getRestaurantById(Long id);
     void deleteUser(Long id); 

}
