package com.example.nicolaspuebla_proyecto_final.model;

import java.sql.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "training")
public class Training extends Event {

    public Training(){}

    public Training(Team team_id, String address, int latitude, int longitude, Date date) {
        super(team_id, address, latitude, longitude, date);
    }
}
