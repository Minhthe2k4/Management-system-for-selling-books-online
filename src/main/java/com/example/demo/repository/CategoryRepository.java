package com.example.demo.repository;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT new com.example.demo.DTO.CategoryDTO(id, ten) FROM Category")
    List<CategoryDTO> getNameCategory();



}
