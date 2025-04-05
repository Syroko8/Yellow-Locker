package com.example.nicolaspuebla_proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("select t from Token t where t.token = ?1")
    Token getTokenByToken(String token);

    @Query("select t from Token t where t.user_id = ?1")
    Token getTokenByUserId(Long userId);

    @Query("delete from Token t where t.token = ?1")
    Token deleteByToken(String token);
}
