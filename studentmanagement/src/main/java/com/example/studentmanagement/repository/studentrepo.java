package com.example.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentmanagement.model.student;

public interface studentrepo extends JpaRepository<student,Long> {

    Optional<student> findByName(String name);

}
