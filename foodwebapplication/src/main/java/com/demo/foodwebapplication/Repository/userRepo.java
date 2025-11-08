 package com.demo.foodwebapplication.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.foodwebapplication.model.user;



@Repository
public interface userRepo extends JpaRepository<user,Long> {
    Optional<user> findByName(String name);

}
