package com.demo.foodwebapplication.ServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public void deleteRestaurant(Long id) {
        restaurantRepo.deleteById(id);
    }

    @Override
    public restaurant addRestaurant(restaurant r) {
        return restaurantRepo.save(r);
    }

    // for the image upload

    
    private final String uploadDir = "src/main/resources/static/images/restaurants/";

    @Override
    public String saveRestaurantImage(long id, MultipartFile file) throws IOException {
        restaurant restaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Save relative path to DB
        restaurant.setImagepath(fileName + "/images/restaurants/");
        restaurantRepo.save(restaurant);

        return fileName;
    }
    
}
