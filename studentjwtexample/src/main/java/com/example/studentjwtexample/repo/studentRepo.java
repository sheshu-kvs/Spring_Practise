package com.example.studentjwtexample.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentjwtexample.model.student;

@Repository
public interface studentRepo extends JpaRepository<student, Long>{

    
}
