package com.example.nicolaspuebla_proyecto_final.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "player")
public class Player extends TeamRol {

    public Player(){}

    public Player(User user, Team team) {
        super(user, team);
    }
}
