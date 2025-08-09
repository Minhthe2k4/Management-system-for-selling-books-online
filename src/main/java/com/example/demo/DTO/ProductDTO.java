package com.example.demo.DTO;

public class ProductDTO {
    private Long id;
    private String ten;
    private String hinh_anh;
    private Integer so_luong_ton;
    private String ten_danh_muc;
    private Double gia;

    public ProductDTO(Long id, String ten, String hinh_anh, Integer so_luong_ton, String ten_danh_muc, Double gia) {
        this.id = id;
        this.ten = ten;
        this.hinh_anh = hinh_anh;
        this.so_luong_ton = so_luong_ton;
        this.ten_danh_muc = ten_danh_muc;
        this.gia = gia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinh_anh() {
        return hinh_anh;
    }

    public void setHinh_anh(String hinh_anh) {
        this.hinh_anh = hinh_anh;
    }

    public Integer getSo_luong_ton() {
        return so_luong_ton;
    }

    public void setSo_luong_ton(Integer so_luong_ton) {
        this.so_luong_ton = so_luong_ton;
    }

    public String getTen_danh_muc() {
        return ten_danh_muc;
    }

    public void setTen_danh_muc(String ten_danh_muc) {
        this.ten_danh_muc = ten_danh_muc;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }
}
