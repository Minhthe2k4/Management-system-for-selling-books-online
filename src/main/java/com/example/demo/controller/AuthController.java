package com.example.demo.controller;


import com.example.demo.DTO.Dto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerPage(Model model) {
        // Pass an empty DTO to the form for binding
        model.addAttribute("userDto", new Dto());
        return "auth/register"; // Assuming your Thymeleaf template is in src/main/resources/templates/auth/register.html
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userDto") Dto userDto, RedirectAttributes ra) {
        // --- Server-side validation for confirm password ---
        if (!userDto.getMatKhau().equals(userDto.getConfirmMatKhau())) {
            ra.addFlashAttribute("error", "Mật khẩu và xác nhận mật khẩu không khớp!");
            ra.addFlashAttribute("userDto", userDto); // Keep user's input
            return "redirect:/register";
        }

        // --- Server-side validation for terms acceptance ---
        if (!userDto.isAcceptTerms()) {
            ra.addFlashAttribute("error", "Bạn phải đồng ý với điều khoản dịch vụ và chính sách bảo mật!");
            ra.addFlashAttribute("userDto", userDto); // Keep user's input
            return "redirect:/register";
        }


        // --- Map DTO to User entity ---
        User user = new User();
        user.setHo(userDto.getHo());
        user.setTen(userDto.getTen());
        user.setEmail(userDto.getEmail());
        user.setMatKhau(userDto.getMatKhau()); // UserService will encode this
        user.setDiaChi(userDto.getDiaChi());
        user.setSoDienThoai(userDto.getSoDienThoai());
        user.setTrangThaiHoatDong(true);

        if (userService.dangKy(user)) {
            ra.addFlashAttribute("success", "Đăng ký tài khoản thành công! Bạn có thể đăng nhập ngay.");
            return "redirect:/login";
        } else {
            ra.addFlashAttribute("error", "Email đã tồn tại. Vui lòng sử dụng email khác.");
            ra.addFlashAttribute("userDto", userDto); // Keep user's input
            return "redirect:/register";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login"; // Assuming your Thymeleaf template is in src/main/resources/templates/auth/login.html
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }




}
