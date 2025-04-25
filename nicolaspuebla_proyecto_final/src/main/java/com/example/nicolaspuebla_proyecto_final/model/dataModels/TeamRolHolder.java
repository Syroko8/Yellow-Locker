package com.example.nicolaspuebla_proyecto_final.model.dataModels;

public class TeamRolHolder {
    private MobileUser user;
    private Team team;

    public TeamRolHolder() {}

    public TeamRolHolder(MobileUser user, Team team) {
        this.user = user;
        this.team = team;
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
}
