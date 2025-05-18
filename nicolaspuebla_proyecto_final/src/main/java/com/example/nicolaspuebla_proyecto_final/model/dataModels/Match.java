package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "match")
public class Match extends Event {

    @ManyToOne
    @JsonIgnoreProperties("matchesAsOpponent")
    @JoinColumn(name = "opponent_team_id")    
    private Team opponent;
    private Integer own_goals = 0;
    private Integer opponent_goals = 0;

    public Match(){}

    public Match(Team team, String address, Double latitude, Double longitude, String date, Team opponent, int own_goals, int opponent_goals) {
        super(team, address, latitude, longitude, date);
        this.opponent = opponent;
        this.own_goals = own_goals;
        this.opponent_goals = opponent_goals;
    }

    public Team getOpponent() {
        return opponent;
    }

    public void setOpponent(Team opponent) {
        this.opponent = opponent;
    }

    public int getOwnGoals() {
        return own_goals;
    }

    public void setOwnGoals(int own_goals) {
        this.own_goals = own_goals;
    }

    public int getOpponentGoals() {
        return opponent_goals;
    }

    public void setOpponentGoals(int opponent_goals) {
        this.opponent_goals = opponent_goals;
    }
}
