package com.example.demo.DTO;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class OrderDTO {
    private Long id;
    private String ten_nguoi_dung;
    private String ten_san_pham;
    private Integer so_luong;
    private String so_dien_thoai;
    private Double gia;
    private LocalDateTime ngay_dat;
    private String dia_chi;
    private Boolean trang_thai;


    public OrderDTO(Long id, String ten_nguoi_dung, String ten_san_pham, Integer so_luong,
                    String so_dien_thoai, Double gia, LocalDateTime ngay_dat,
                    String dia_chi, Boolean trang_thai) {
        this.id = id;
        this.ten_nguoi_dung = ten_nguoi_dung;
        this.ten_san_pham = ten_san_pham;
        this.so_luong = so_luong;
        this.so_dien_thoai = so_dien_thoai;
        this.gia = gia;
        this.ngay_dat = ngay_dat;
        this.dia_chi = dia_chi;
        this.trang_thai = trang_thai;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen_nguoi_dung() {
        return ten_nguoi_dung;
    }

    public void setTen_nguoi_dung(String ten_nguoi_dung) {
        this.ten_nguoi_dung = ten_nguoi_dung;
    }

    public String getTen_san_pham() {
        return ten_san_pham;
    }

    public void setTen_san_pham(String ten_san_pham) {
        this.ten_san_pham = ten_san_pham;
    }

    public Integer getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(Integer so_luong) {
        this.so_luong = so_luong;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public LocalDateTime getNgay_dat() {
        return ngay_dat;
    }

    public void setNgay_dat(LocalDateTime ngay_dat) {
        this.ngay_dat = ngay_dat;
    }

    public Boolean getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(Boolean trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }
}
