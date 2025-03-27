package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private MobileUser user_id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team_id;

    public Message(){}

    public Message(MobileUser user, Team team){
        this.user_id = user;
        this.team_id = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MobileUser getUser_id() {
        return user_id;
    }

    public void setUser_id(MobileUser user_id) {
        this.user_id = user_id;
    }

    public Team getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Team team_id) {
        this.team_id = team_id;
    }
}
