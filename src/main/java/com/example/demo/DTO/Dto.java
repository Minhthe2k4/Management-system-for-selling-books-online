package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// You can add validation annotations here if you use Spring's @Valid
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dto {
    // These fields must match the 'name' attributes in your HTML form
    private String ho;
    private String ten;
    private String email;
    private String matKhau;
    private String confirmMatKhau; // This is the new field to match your form
    private String diaChi;
    private String soDienThoai;
    private boolean acceptTerms; // To capture the checkbox state
}