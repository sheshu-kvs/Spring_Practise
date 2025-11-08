package com.demo.foodwebapplication.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.foodwebapplication.model.cart;
import com.demo.foodwebapplication.model.user;

public interface cartRepo extends JpaRepository<cart,Long> {
       Optional<cart> findByUser(user user);
}
