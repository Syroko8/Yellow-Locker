package com.example.nicolaspuebla_proyecto_final.model.dto;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Locality;

public class TeamCreation {
    private Long userId;
    private String teamName;
    private Locality teamLocality;
    private String teamSport;

    public TeamCreation() {}

    public TeamCreation(Long userId, String teamName, Locality teamLocality, String teamSport) {
        this.userId = userId;
        this.teamName = teamName;
        this.teamLocality = teamLocality;
        this.teamSport = teamSport;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Locality getTeamLocality() {
        return teamLocality;
    }

    public void setTeamLocality(Locality teamLocality) {
        this.teamLocality = teamLocality;
    }

    public String getTeamSport() {
        return teamSport;
    }

    public void setTeamSport(String teamSport) {
        this.teamSport = teamSport;
    }
}
