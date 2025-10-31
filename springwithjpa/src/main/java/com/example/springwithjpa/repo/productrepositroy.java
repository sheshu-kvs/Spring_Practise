package com.example.springwithjpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springwithjpa.model.product;

@Repository


public interface  productrepositroy extends JpaRepository<product,Long>{

}
