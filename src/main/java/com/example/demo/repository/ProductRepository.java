package com.example.demo.repository;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new com.example.demo.DTO.ProductDTO(sp.id, sp.ten, sp.hinh_anh, sp.so_luong_ton, dm.ten, sp.gia) FROM Product sp JOIN sp.category dm")
    List<ProductDTO> getPartOfInfoProduct();


    void deleteById(Long id);





}
