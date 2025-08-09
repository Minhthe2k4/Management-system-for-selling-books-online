package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean dangKy(User user) {
        // Check if a user with the given email already exists
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            return false;
        }

        // Encode the user's password before saving
        user.setMatKhau(passwordEncoder.encode(user.getMatKhau()));
        user.setCreatedAt(LocalDateTime.now());

        // Assign the default "USER" role
        Role defaultRole = roleRepo.findByTen("USER")
                .orElseThrow(() -> new NoSuchElementException("Role 'USER' not found in the database. Please ensure it's pre-populated."));
        user.getRole().add(defaultRole);

        userRepo.save(user);
        return true;
    }
}