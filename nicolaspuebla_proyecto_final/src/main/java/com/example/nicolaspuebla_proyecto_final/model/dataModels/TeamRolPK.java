package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class TeamRolPK implements Serializable {

    private long userId;
    private long teamId;
    
    public TeamRolPK(){}

    public TeamRolPK(Long userId, Long teamId){
        this.userId = userId;
        this.teamId = teamId;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public long getTeamId() {
        return teamId;
    }
    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
}
