package com.example.nicolaspuebla_proyecto_final.repository;

import com.example.nicolaspuebla_proyecto_final.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
