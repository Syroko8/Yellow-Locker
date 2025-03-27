package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "player")
public class Player extends TeamRol {

    public Player(){}

    public Player(MobileUser user, Team team) {
        super(user, team);
    }
}
