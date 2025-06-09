package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Clase que representa la entidad Capitán, que hereda de TeamRol.
 */
@Entity
@DiscriminatorValue(value = "captain")
public class Captain extends TeamRol {

    public Captain(){}

    public Captain(MobileUser user, Team team) {
        super(user, team);
    }
}
