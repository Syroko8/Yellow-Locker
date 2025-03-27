package com.example.nicolaspuebla_proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("select u from User u where u.email = ?1")
    User findUserByEmail(String userEmail);
}
