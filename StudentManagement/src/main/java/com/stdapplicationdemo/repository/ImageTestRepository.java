package com.stdapplicationdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stdapplicationdemo.model.ImageTest;

public interface ImageTestRepository extends JpaRepository<ImageTest, Integer> {

}
