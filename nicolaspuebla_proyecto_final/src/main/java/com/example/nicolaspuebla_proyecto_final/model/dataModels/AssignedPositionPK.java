package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

/**
 * Clase para la clave primaria compuesta de AssignedPosition.
 */
@Embeddable
public class AssignedPositionPK implements Serializable {
    
    private Long user_id;
    private Long team_id;

    public AssignedPositionPK(){}

    public AssignedPositionPK(Long user_id, Long team_id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssignedPositionPK)) return false;
        AssignedPositionPK pk = (AssignedPositionPK) o;
        return Objects.equals(user_id, pk.user_id) &&
               Objects.equals(team_id, pk.team_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, team_id);
    }
}
