package com.example.nicolaspuebla_proyecto_final.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "coach")
public class Coach extends TeamRol {

    public Coach(){}

    public Coach(User user, Team team) {
        super(user, team);
    }
}
