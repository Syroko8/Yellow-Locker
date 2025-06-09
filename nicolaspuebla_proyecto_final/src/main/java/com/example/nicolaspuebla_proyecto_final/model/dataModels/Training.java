package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Clase que representa la entidad Training, que hereda de Event.
 */
@Entity
@DiscriminatorValue(value = "training")
public class Training extends Event {

    public Training(){}

    public Training(Team team_id, Double latitude, Double longitude, String date) {
        super(team_id, latitude, longitude, date);
    }
}
