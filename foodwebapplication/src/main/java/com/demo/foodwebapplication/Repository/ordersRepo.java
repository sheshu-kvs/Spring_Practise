package com.demo.foodwebapplication.Repository;

import org.springframework.stereotype.Repository;
import com.demo.foodwebapplication.model.orders;
import com.demo.foodwebapplication.model.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface  ordersRepo extends JpaRepository<orders,Long> {
  //Find all orders placed by a specific user
    List<orders> findByUser(user user);
}
