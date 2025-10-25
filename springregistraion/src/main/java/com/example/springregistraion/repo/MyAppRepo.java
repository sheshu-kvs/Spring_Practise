package com.example.springregistraion.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springregistraion.model.MyAppuser;

@Repository
public interface MyAppRepo extends JpaRepository<MyAppuser,Long>{

    Optional<MyAppuser> findByUsername(String username);
}
