package com.example.nicolaspuebla_proyecto_final.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class TeamRolPK implements Serializable {

    private long user_id;
    private long team_id;
    
    public TeamRolPK(){}

    public TeamRolPK(Long user_id, Long team_id){
        this.user_id = user_id;
        this.team_id = team_id;
    }

    public long getUserId() {
        return user_id;
    }
    public void setUserId(long userId) {
        this.user_id = userId;
    }
    public long getTeamId() {
        return team_id;
    }
    public void setTeamId(long teamId) {
        this.team_id = teamId;
    }
}
