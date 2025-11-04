package com.demo.foodwebapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.foodwebapplication.model.restaurant;
import org.springframework.stereotype.Repository;

@Repository
public interface  restaurantRepo extends JpaRepository<restaurant,Long> {

}
