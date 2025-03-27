package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull
    private String name;
    @ManyToOne
    @JoinColumn(name = "locality")
    private Locality locality;
    @NonNull
    private String logo;
    @NonNull
    private String chatKey;
    @NonNull
    private String sport;
    @OneToMany(mappedBy = "team_id")
    private List<TeamPositions> positions;
    @OneToMany(mappedBy = "team_id")
    private List<Message> messages;
    @OneToMany(mappedBy = "team_id")
    private List<TeamRol> teamRoles; 
    @OneToMany(mappedBy = "team_id")
    private List<Event> eventList;
    @ManyToMany(mappedBy = "teamList")
    private List<MobileUser> members = new ArrayList<>();

    public Team(){}

    public Team(String name, Locality locality, String logo, String chatKey, String sport) {
        this.name = name;
        this.locality = locality;
        this.logo = logo;
        this.chatKey = chatKey;
        this.sport = sport;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getChatKey() {
        return chatKey;
    }

    public void setChatKey(String chatKey) {
        this.chatKey = chatKey;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}
