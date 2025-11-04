package com.demo.foodwebapplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.foodwebapplication.model.food_item;


@Repository
public interface food_itemRepo extends JpaRepository<food_item, Long> {

}
