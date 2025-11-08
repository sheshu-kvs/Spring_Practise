package com.demo.foodwebapplication.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.foodwebapplication.Service.fooditemService;
import com.demo.foodwebapplication.model.food_item;

@RestController
@RequestMapping("/food")
public class foodItemController{

    @Autowired
    public fooditemService foodservice;

    @PostMapping("/add")
    public food_item addFood(@RequestBody food_item food){
        return foodservice.saveFood(food);
    }
    
    @GetMapping("/all")
    public List<food_item> getAllFoods(){
        return foodservice.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFoodById(@PathVariable long id){
        food_item food = foodservice.getFoodById(id);
        if(food == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter Food Id Was Not Found");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body("Enter the Food Id Was Found");
    }
    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable long id){
        foodservice.deleteFoodById(id);
    }

    @GetMapping("/restaurant/{id}")
   public ResponseEntity<?> getFoodsByRestaurant(@PathVariable("id") long restaurantId) {
List<food_item> foodList = foodservice.getFoodsByRestaurantId(restaurantId);

if (foodList.isEmpty()) {
return ResponseEntity.status(HttpStatus.NOT_FOUND)
.body("No food items found for this restaurant ID: " + restaurantId);
}   

return ResponseEntity.ok(foodList);
}


    @PutMapping
    public food_item updateFood(@RequestBody food_item f){
        return foodservice.updateFood(f);
    }
      @PostMapping("/uploadImage/{id}")
    public ResponseEntity<String> uploadFoodImage(
            @PathVariable long id,
            @RequestParam("file") MultipartFile file) {
        try {
            String fileName = foodservice.saveFoodImage(id, file);
            return ResponseEntity.ok("Food image uploaded: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error uploading image");
        }
    }

}