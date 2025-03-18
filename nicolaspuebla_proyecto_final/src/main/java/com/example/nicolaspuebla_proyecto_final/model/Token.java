package com.example.nicolaspuebla_proyecto_final.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Token {

    @Id
    private Long token;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    public Token(){}
    
    public Token(User user){
        this.user_id = user;

        /* Generar token y asignarlo en el atributo, al introducirlo en la base de datos, ver si da un error de id duplicado, si da,
        genero otro token y lo asigno.*/



    }
}
