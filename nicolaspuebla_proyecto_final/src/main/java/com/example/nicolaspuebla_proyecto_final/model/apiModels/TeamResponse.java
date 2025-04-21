package com.example.nicolaspuebla_proyecto_final.model.apiModels;

import java.util.ArrayList;
import java.util.List;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Event;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.Locality;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamPosition;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.TeamRol;
import com.example.nicolaspuebla_proyecto_final.model.dataModels.User;

public class TeamResponse {
    private Long id;
    private String name;
    private Locality locality;
    private String logo;
    private String chatkey;
    private String sport;
    private List<TeamPosition> positions = new ArrayList<>();
    private List<Long> messages = new ArrayList<>();
    private List<TeamRol> teamRoles = new ArrayList<>();
    private List<Event> eventList = new ArrayList<>();
    private List<User> members = new ArrayList<>();

    public TeamResponse(){}

    public TeamResponse(Long id, String name, Locality locality, String logo, String chatkey, String sport) {
        this.id = id;
        this.name = name;
        this.locality = locality;
        this.logo = logo;
        this.chatkey = chatkey;
        this.sport = sport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getChatkey() {
        return chatkey;
    }

    public void setChatkey(String chatkey) {
        this.chatkey = chatkey;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public List<TeamPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<TeamPosition> positions) {
        this.positions = positions;
    }

    public List<Long> getMessages() {
        return messages;
    }

    public void setMessages(List<Long> messages) {
        this.messages = messages;
    }

    public List<TeamRol> getTeamRoles() {
        return teamRoles;
    }

    public void setTeamRoles(List<TeamRol> teamRoles) {
        this.teamRoles = teamRoles;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
