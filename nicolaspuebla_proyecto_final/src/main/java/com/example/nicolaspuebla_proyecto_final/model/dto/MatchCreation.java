package com.example.nicolaspuebla_proyecto_final.model.dto;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;

/**
 * Clase que contiene la información necesaria para la creación de un partido.
 */
public class MatchCreation extends EventCreation {
    private Team opponent;
    private int ownGoals;
    private int opponentGoals;

    public MatchCreation(){}

    public MatchCreation(String event_type, Long teamId, Double latitude, Double longitude, String date, Team opponent, int ownGoals, int opponentGoals) {
        super(event_type, teamId, latitude, longitude, date);
        this.opponent = opponent;
        this.ownGoals = ownGoals;
        this.opponentGoals = opponentGoals;
    }

    public Team getOpponent() {
        return opponent;
    }

    public void setOpponent(Team opponent) {
        this.opponent = opponent;
    }

    public int getOwnGoals() {
        return ownGoals;
    }

    public void setOwnGoals(int ownGoals) {
        this.ownGoals = ownGoals;
    }

    public int getOpponentGoals() {
        return opponentGoals;
    }

    public void setOpponentGoals(int opponentGoals) {
        this.opponentGoals = opponentGoals;
    }   
}
