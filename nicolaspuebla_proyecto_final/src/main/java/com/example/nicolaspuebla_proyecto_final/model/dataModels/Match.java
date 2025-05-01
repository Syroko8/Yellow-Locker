package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "match")
public class Match extends Event {

    @ManyToOne
    @JsonIgnoreProperties("matchesAsOponent")
    @JoinColumn(name = "oponent_team_id")    
    private Team oponent;
    private int own_goals;
    private int oponent_goals;

    public Match(){}

    public Match(Team team, String address, int latitude, int longitude, Date date, Team oponent, int own_goals, int oponent_goals) {
        super(team, address, latitude, longitude, date);
        this.oponent = oponent;
        this.own_goals = own_goals;
        this.oponent_goals = oponent_goals;
    }

    public Team getOponent() {
        return oponent;
    }

    public void setOponent(Team oponent) {
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
