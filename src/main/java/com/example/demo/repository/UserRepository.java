package com.example.demo.repository;

import com.example.demo.DTO.UserDTO;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT new com.example.demo.DTO.UserDTO(u.id, u.ten, u.matKhau, u.email, u.diaChi, u.soDienThoai, r.ten, u.trangThaiHoatDong ) FROM User u JOIN u.role r")
    List<UserDTO> getAllAccounts();

    Optional<User> findById(Long id);
}
