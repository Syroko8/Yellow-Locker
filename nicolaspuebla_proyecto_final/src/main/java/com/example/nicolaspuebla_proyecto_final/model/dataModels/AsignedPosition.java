package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class AsignedPosition {
    
    @EmbeddedId
    private AsignedPositionPK id;
    
    @OneToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private MobileUser user;
    
    @ManyToOne
    @MapsId("team_id")   
    @JoinColumn(name = "team_id")
    private Team team;

    private Long position_id;

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

    public Long getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Long position_id) {
        this.position_id = position_id;
    }    
}
