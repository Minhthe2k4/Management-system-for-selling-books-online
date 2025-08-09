package com.example.demo.DTO;

public class ProductSortDTO {
    private Long id;
    private String ten;
    private String hinh_anh;
    private Double gia;

    public ProductSortDTO(Long id, String ten, String hinh_anh, Double gia) {
        this.id = id;
        this.ten = ten;
        this.hinh_anh = hinh_anh;
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

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }
}
