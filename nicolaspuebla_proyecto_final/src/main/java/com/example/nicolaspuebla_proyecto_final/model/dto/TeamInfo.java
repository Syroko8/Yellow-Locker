package com.example.nicolaspuebla_proyecto_final.model.dto;

import java.util.List;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Event;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Team;

public class TeamInfo {
    private Team team;
    private List<Event> events;

    public TeamInfo(){}

    public TeamInfo(Team team, List<Event> events) {
        this.team = team;
        this.events = events;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
