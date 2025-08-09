package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "san_pham")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ten;
    private String mo_ta;
    private Double gia;
    private Integer so_luong_ton;
    private String hinh_anh;


    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "danh_muc_id")
    private Category category;

    @OneToMany(mappedBy = "products" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailedOrder> detailedOrders;




}
