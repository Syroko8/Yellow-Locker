package com.example.nicolaspuebla_proyecto_final.model.dto;

public class LeaveTeamRequest {
    private Long userId;
    private Long teamId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public LeaveTeamRequest(){}

    public LeaveTeamRequest(Long userId, Long teamId) {
        this.userId = userId;
        this.teamId = teamId;
    }
}
