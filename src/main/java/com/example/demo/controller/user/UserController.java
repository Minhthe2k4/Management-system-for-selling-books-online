package com.example.demo.controller.user;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model; // Nếu bạn cần truyền dữ liệu đến view
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

@Controller // Đảm bảo đây là một Spring Controller
@RequestMapping("/user") // Đặt base path cho các URL bắt đầu bằng /user
public class UserController {

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public CategoryRepository categoryRepository;


    @GetMapping("/Cuahang")
    public String showCuahangPage(Model model) {
        List<CategoryDTO> categories = categoryRepository.getNameCategory();
        model.addAttribute("categories", categories);

        List<ProductDTO> products = productRepository.getPartOfInfoProduct();
        model.addAttribute("products", products);

        return "user/Cuahang";
    }

    @GetMapping("/product/detail/{id}")
    public String productDetailedInfo(@PathVariable("id") Long id, Model model) {
        Optional<Product> products = productRepository.findById(id);
        if(products.isPresent()) {
            Product product = products.get();
            model.addAttribute("productinfo", product);
            return "user/product";
        } else {
            return "redirect:/user/product?error=notfound";
        }
    }

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    private UserRepository userRepository;

    @PostMapping("/product/add")
    public String addToCart(
            @RequestParam("userId") Long userId,
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity,
            RedirectAttributes redirectAttributes,
            Model model
    ) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));
        cartService.addToCart(userId, productId, quantity);

        redirectAttributes.addFlashAttribute("message", "Đã thêm sản phẩm vào giỏ hàng!");
        model.addAttribute("product", product);
        model.addAttribute("user", user);
        return "redirect:/user/Cuahang";
    }

    @GetMapping("/about")
    public String WebsiteInfoAppearance(Model model) {
        return "user/about";
    }

    @GetMapping("/shopping-card")
    public String CartAppearance(Model model) {
        return "user/shopping-cart";
    }

    @GetMapping("/index")
    public String WebpageAppearance(Model model) {
        return "user/index";
    }

    @GetMapping("/contact")
    public String ContactAppearance(Model model) {
        return "user/contact";
    }






}
