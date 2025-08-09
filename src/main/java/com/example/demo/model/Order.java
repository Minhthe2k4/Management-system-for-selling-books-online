package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "don_hang")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime ngay_dat;
    private Boolean trang_thai;
    private Double tong_tien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nguoi_dung_id")
    private User users;

    @OneToMany(mappedBy = "orders" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailedOrder> detailedOrders;


}
