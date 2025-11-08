package com.demo.foodwebapplication.ServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.foodwebapplication.Repository.food_itemRepo;
import com.demo.foodwebapplication.Service.fooditemService;
import com.demo.foodwebapplication.model.food_item;


@Service
public class fooditemServiceImpl implements fooditemService{

    @Autowired
    public food_itemRepo foodItemRepo;

    @Override
    public food_item saveFood(food_item f) {
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
    public void deleteFoodById(Long id) {
        foodItemRepo.deleteById(id);

    }

    @Override
    public food_item updateFood(food_item f) {

        Optional<food_item> foodOpt = foodItemRepo.findById(f.getFood_id());
        if(foodOpt.isEmpty()){
            throw new  ResourceNotFoundException("Entered food was not Found..");
        }
        food_item ExistingfoodObj = foodOpt.get();
         ExistingfoodObj.setName(( f.getName()));
         ExistingfoodObj.setPrice( f.getPrice());
         ExistingfoodObj.setDescription((f.getDescription()));
         ExistingfoodObj.setRestaurantId(f.getRestaurantId());

         return foodItemRepo.save(ExistingfoodObj);

        
    }

    @Override
    public List<food_item> getFoodsByRestaurantId(long restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }


     private final String uploadDir = "src/main/resources/static/images/food/";

    @Override
    public String saveFoodImage(long id, MultipartFile file) throws IOException {
        food_item food = foodItemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Save relative path to DB
        food.setImagepath("/images/food/" + fileName);
        foodItemRepo.save(food);

        return fileName;
    }
    
}
