package com.example.demo.repository;


import com.example.demo.DTO.RoleDTO;
import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByTen(String ten);

    @Query("SELECT new com.example.demo.DTO.RoleDTO(id, ten) FROM Role")
    List<RoleDTO> getNameRole();
}
