package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TeamRolPK implements Serializable {


    @Column(name = "user_id")
    private long userId;

    @Column(name = "team_id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamRolPK teamRolPK = (TeamRolPK) o;
        return userId == teamRolPK.userId && teamId == teamRolPK.teamId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, teamId);
    }
}
