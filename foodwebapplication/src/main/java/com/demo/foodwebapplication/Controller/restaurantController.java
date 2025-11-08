package com.demo.foodwebapplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.foodwebapplication.Service.restaurantService;
import com.demo.foodwebapplication.model.restaurant;

@RestController
@RequestMapping("/res")
public class restaurantController{
    @Autowired
    public restaurantService restServise;

    @PostMapping("/add")
    public restaurant AddRes(@RequestBody restaurant r){
        return restServise.addRestaurant(r);
    }
    // get All 
    @GetMapping("/all")
    public List<restaurant> getAll(){
        return restServise.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getResById(@PathVariable long id){
        restaurant res = restServise.getRestaurantById(id);
        if(res==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entered Id Was No Found");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body("Entered Id Was Found..");
    }


    

    @DeleteMapping("/del/{id}")
    public void deleteReById(@PathVariable long id){
      restServise.deleteRestaurant(id);
    }


    //for the img upload...

      @PostMapping("/uploadImage/{id}")
    public ResponseEntity<String> uploadRestaurantImage(
            @PathVariable long id,
            @RequestParam("file") MultipartFile file) {
        try {
            String fileName =  restServise.saveRestaurantImage(id, file);
            return ResponseEntity.ok("Restaurant image uploaded: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error uploading image");
        }
    }

  
    

}