package com.example.demo.DTO;

public class UserDTO {

    private Long id;
    private String ten;
    private String matKhau;
    private String email;
    private String diaChi;
    private String soDienThoai;
    private String tenQuyen;
    private Boolean trangThaiHoatDong;

    public UserDTO(Long id, String ten, String matKhau, String email, String diaChi, String soDienThoai, String tenQuyen, Boolean trangThaiHoatDong) {
        this.id = id;
        this.ten = ten;
        this.matKhau = matKhau;
        this.email = email;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.tenQuyen = tenQuyen;
        this.trangThaiHoatDong = trangThaiHoatDong;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    public Boolean getTrangThaiHoatDong() {
        return trangThaiHoatDong;
    }

    public void setTrangThaiHoatDong(Boolean trangThaiHoatDong) {
        this.trangThaiHoatDong = trangThaiHoatDong;
    }
}
