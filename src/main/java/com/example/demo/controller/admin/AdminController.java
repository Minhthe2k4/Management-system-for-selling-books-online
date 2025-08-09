package com.example.demo.controller.admin;

import com.example.demo.DTO.*;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/bieudo")
    public String showHomePage() {
        return "admin/bieudo";
    }

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;

    @GetMapping("/productquantri")
    public String productApperance(@RequestParam(value = "editId", required = false) Long editId, Model model) {
        List<ProductDTO> products = productRepository.getPartOfInfoProduct();
        List<CategoryDTO> categories = categoryRepository.getNameCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);

        if (editId != null) {
            Optional<Product> optional = productRepository.findById(editId);
            optional.ifPresent(product -> model.addAttribute("editingProduct", product));
        }

        return "admin/productquantri";
    }

    @GetMapping("/account")
    public String accountAppearance(@RequestParam(value = "editId", required = false) Long editId, Model model) {
        List<UserDTO> users = userRepository.getAllAccounts();
        List<RoleDTO> roles = roleRepository.getNameRole();
        model.addAttribute("accounts", users);
        model.addAttribute("roles", roles);

        if (editId != null) {
            Optional<User> optional1 = userRepository.findById(editId);
            optional1.ifPresent(user -> model.addAttribute("editingAccount", user));
        } else {
            model.addAttribute("editingAccount", new User());
        }
        return "admin/account";
    }

    @GetMapping("/category")
    public String categoryAppearance(@RequestParam(value = "editId", required = false) Long editId ,Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        if(editId != null) {
            Optional<Category> ca = categoryRepository.findById(editId);
            ca.ifPresent(category -> model.addAttribute("editingCategory", category));
        }
        return "admin/category";
    }




    @GetMapping("/Donhangquantri")
    public String orderAppearance(Model model) {
        List<OrderDTO> orderDTOS = orderRepository.getPartOfTheOrder();
        model.addAttribute("orders", orderDTOS);
        return "admin/Donhangquantri";
    }

    @GetMapping("/Quantrikhuyenmai")
    public String preferenceAppearance(Model model) {
        return "admin/Quantrikhuyenmai";
    }

    @GetMapping("/Quanlidanhgia")
    public String rateAppearance(Model model) {
        return "admin/Quanlidanhgia";
    }

    @GetMapping("/Chitietdanhmuc/detail/{id}")
    public String productDetailedInfo(@PathVariable("id") Long id,  Model model) {
        Optional<Product> products = productRepository.findById(id);
        if(products.isPresent()) {
            Product product = products.get();
            model.addAttribute("productinfo", product);
            return "admin/Chitietdanhmuc";
        } else {
            return "redirect:/admin/Chitietdanhmuc?error=notfound";
        }
    }

    //RequestParam thi cac truong phai trung voi name trong cac the trong HTML???
    @PostMapping("/productquantri/update")
    public String updateProduct(@RequestParam(value = "id", required = false) Long id,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "description", required = false) String description,
                                @RequestParam(value = "price", required = false) Double price,
                                @RequestParam(value = "quantity", required = false) Integer quantity,
                                @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime createdAt,
                                @RequestParam Long category
                                ) {
        Optional<Product> products = productRepository.findById(id);
        if(products.isPresent()) {
            Product product = products.get();
            product.setTen(name);
            product.setGia(price);
            product.setMo_ta(description);
            product.setSo_luong_ton(quantity);
            categoryRepository.findById(category).ifPresent(product::setCategory);
            product.setCreatedAt(createdAt);

            if (imageFile != null && !imageFile.isEmpty()) {
                try {
                    String fileName = imageFile.getOriginalFilename();
                    Path uploadPath = Paths.get("savedimage/");
                    Files.createDirectories(uploadPath);
                    Files.copy(imageFile.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                    product.setHinh_anh("/savedimage/" + fileName);
                } catch (IOException e) {
                    e.printStackTrace(); // nên log lỗi
                }
            }
            productRepository.save(product);
        }
        return "redirect:/admin/productquantri";
    }

    @PostMapping("/productquantri/add")
    public String addProduct(

            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "quantity", required = false) Integer quantity,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            //@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime createdAt,
            @RequestParam Long category)
    {
        Product p = new Product();
        p.setTen(name);
        p.setMo_ta(description);
        p.setGia(price);
        p.setSo_luong_ton(quantity);
        categoryRepository.findById(category).ifPresent(p::setCategory);

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = imageFile.getOriginalFilename();
                Path uploadPath = Paths.get("savedimage/");
                Files.createDirectories(uploadPath);
                Files.copy(imageFile.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                p.setHinh_anh("/savedimage/" + fileName);
            } catch (IOException e) {
                e.printStackTrace(); // nên log lỗi
            }
        }
        productRepository.save(p);

        return "redirect:/admin/productquantri";

    }

    @PostMapping("/productquantri/delete")
    public String deleteProduct(@RequestParam(value = "id", required = false) Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            productRepository.deleteById(id);
        }
        return "redirect:/admin/productquantri";
    }

    @PostMapping("/account/edit")
    public String editAccount(@RequestParam(value = "id") Long id,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "trangThaiHoatDong", required = false) Boolean trangThaiHoatDong,
                              @RequestParam(value = "roleIds", required = false) Set<Long> roleIds) {

        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Cập nhật trạng thái hoạt động
            user.setId(id);
            user.setTen(name);
            user.setTrangThaiHoatDong(trangThaiHoatDong);

            // Cập nhật danh sách cua role
            Set<Role> roles = new HashSet<>(roleRepository.findAllById(roleIds));
            user.setRole(roles);

            userRepository.save(user);
        }

        return "redirect:/admin/account";
    }

    @PostMapping("/account/delete")
    public String deleteAccount(@RequestParam("id") Long id) {
        Optional<User> op = userRepository.findById(id);
        if(op.isPresent()) {
            userRepository.deleteById(id);
        }
        return "redirect:/admin/account";
    }

    @PostMapping("/category/edit")
    public String editCategory(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "ten", required = false) String ten) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()) {
            Category c = category.get();
            c.setTen(ten);
            categoryRepository.save(c);
        }
        return "redirect:/admin/category";
    }

    @PostMapping("/category/delete")
    public String deleteCategory(@RequestParam(value = "id", required = false) Long id) {
        Optional<Category> cate = categoryRepository.findById(id);
        if(cate.isPresent()) {
            categoryRepository.deleteById(id);
        }
        return "redirect:/admin/category";
    }

    @PostMapping("/category/add")
    public String addCategory(@RequestParam(value = "ten", required = false) String ten) {
        Category c = new Category();
        c.setTen(ten);
        categoryRepository.save(c);
        return "redirect:/admin/category";
    }



}
