package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "coach")
public class Coach extends TeamRol {

    public Coach(){}

    public Coach(MobileUser user, Team team) {
        super(user, team);
    }
}
