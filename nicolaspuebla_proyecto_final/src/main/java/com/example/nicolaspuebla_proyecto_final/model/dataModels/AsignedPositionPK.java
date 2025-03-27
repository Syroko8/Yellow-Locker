package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class AsignedPositionPK implements Serializable {
    
    private Long user_id;
    private Long team_id;

    public AsignedPositionPK(){}

    public AsignedPositionPK(Long user_id, Long team_id) {
        this.user_id = user_id;
        this.team_id = team_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Long team_id) {
        this.team_id = team_id;
    }
}
