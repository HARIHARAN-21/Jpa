package com.stdapplicationdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stdapplicationdemo.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
