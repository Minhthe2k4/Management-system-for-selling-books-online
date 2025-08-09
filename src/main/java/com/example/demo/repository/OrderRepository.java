package com.example.demo.repository;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT new com.example.demo.DTO.OrderDTO(dh.id, nd.ten, sp.ten, ctdh.so_luong, nd.soDienThoai, ctdh.gia, dh.ngay_dat, nd.diaChi, dh.trang_thai) FROM Order dh JOIN dh.users nd JOIN dh.detailedOrders ctdh JOIN ctdh.products sp")
    List<OrderDTO> getPartOfTheOrder();
}
