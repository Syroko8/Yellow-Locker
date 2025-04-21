package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rol_type")
public class TeamRol {

    @EmbeddedId
    private TeamRolPK id;

    @ManyToOne
    @MapsId("user_id") 
    @JoinColumn(name = "user_id")
    private MobileUser user;

    @ManyToOne
    @MapsId("team_id") 
    @JoinColumn(name = "team_id")
    private Team team;

    public TeamRol(){}

    public TeamRol(MobileUser user, Team team) {
        this.user = user;
        this.team = team;
    }

    public TeamRolPK getId() {
        return id;
    }

    public void setId(TeamRolPK id) {
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
}
