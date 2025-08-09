package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void addToCart(Long userId, Long productId, Integer quantity) {
        // 1. Lấy product & kiểm tra tồn kho
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        if (product.getSo_luong_ton() < quantity) {
            throw new RuntimeException("Không đủ số lượng tồn kho");
        }

        // 2. Lấy user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));

        // 3. Kiểm tra sản phẩm đã có trong giỏ chưa
        Cart cartItem = cartRepository.findByUserIdAndProductId(userId, productId)
                .orElse(null);

        if (cartItem != null) {
            // Nếu có rồi thì cộng số lượng
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            // Nếu chưa có thì thêm mới
            cartItem = new Cart();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCreatedAt(LocalDateTime.now());
        }

        // 4. Lưu giỏ hàng
        cartRepository.save(cartItem);

        // Nếu muốn trừ tồn kho ngay khi thêm vào giỏ
        // product.setSo_luong_ton(product.getSo_luong_ton() - quantity);
        // productRepository.save(product);
    }
}
