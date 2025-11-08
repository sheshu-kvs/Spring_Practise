package com.demo.foodwebapplication.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.demo.foodwebapplication.model.restaurant;

public interface restaurantService {
     restaurant addRestaurant(restaurant r);
     restaurant saveRestaurant(restaurant r);
     List<restaurant> getAll();
     restaurant getRestaurantById(Long id);
     void deleteRestaurant(Long id);
    String saveRestaurantImage(long id, MultipartFile file) throws IOException;
 

}
