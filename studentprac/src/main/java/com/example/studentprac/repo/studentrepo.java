package com.example.studentprac.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentprac.model.student;

public interface studentrepo extends JpaRepository<student,Long> {
    Optional<student> findByName(String name);
    
}
