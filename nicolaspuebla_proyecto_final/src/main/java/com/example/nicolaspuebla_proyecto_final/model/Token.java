package com.example.nicolaspuebla_proyecto_final.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String Token;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    public Token(){}
    
    public Token(User user){
        this.user_id = user;
        

    }
}
