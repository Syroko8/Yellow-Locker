package com.example.nicolaspuebla_proyecto_final.model;

import jakarta.persistence.Entity;

@Entity
public class Administrator extends User {
    
    public Administrator(){}

    public Administrator(String name, String surname, String email, String password, boolean disabled) {
        super(name, surname, email, password, disabled);
    }
}
