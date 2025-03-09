package com.example.nicolaspuebla_proyecto_final.model;

import java.sql.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "match")
public class Match extends Event {

    private String oponent;
    private int own_goals;
    private int oponent_goals;

    public Match(){}

    public Match(Team team_id, String address, int latitude, int longitude, Date date, String oponent, int own_goals, int oponent_goals) {
        super(team_id, address, latitude, longitude, date);
        this.oponent = oponent;
        this.own_goals = own_goals;
        this.oponent_goals = oponent_goals;
    }

    public String getOponent() {
        return oponent;
    }

    public void setOponent(String oponent) {
        this.oponent = oponent;
    }

    public int getOwn_goals() {
        return own_goals;
    }

    public void setOwn_goals(int own_goals) {
        this.own_goals = own_goals;
    }

    public int getOponent_goals() {
        return oponent_goals;
    }

    public void setOponent_goals(int oponent_goals) {
        this.oponent_goals = oponent_goals;
    }
}
