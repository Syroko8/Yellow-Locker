package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class AsignedPosition {
    
    @EmbeddedId
    private AsignedPositionPK id;
    
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private MobileUser user;
    
    @ManyToOne
    @MapsId("team_id")   
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private TeamPosition position;

    public AsignedPosition() {}

    public AsignedPositionPK getId() {
        return id;
    }

    public void setId(AsignedPositionPK id) {
        this.id = id;
    }

    public MobileUser getUser() {
        return user;
    }

    public void setUser(MobileUser user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public TeamPosition getPosition() {
        return position;
    }

    public void setPosition_id(TeamPosition position) {
        this.position = position;
    }    
}
