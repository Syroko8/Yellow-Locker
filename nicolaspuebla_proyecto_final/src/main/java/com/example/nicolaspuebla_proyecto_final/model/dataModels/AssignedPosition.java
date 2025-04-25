package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class AssignedPosition {
    
    @EmbeddedId
    private AssignedPositionPK id;
    
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

    public AssignedPosition() {}

    public AssignedPosition(MobileUser user, Team team, TeamPosition position){
        this.user = user;
        this.team = team;
        this.position = position;
    }

    public AssignedPositionPK getId() {
        return id;
    }

    public void setId(AssignedPositionPK id) {
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
