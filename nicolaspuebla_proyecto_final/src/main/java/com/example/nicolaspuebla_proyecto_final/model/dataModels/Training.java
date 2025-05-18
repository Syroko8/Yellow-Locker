package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "training")
public class Training extends Event {

    public Training(){}

    public Training(Team team_id, String address, Double latitude, Double longitude, String date) {
        super(team_id, address, latitude, longitude, date);
    }
}
