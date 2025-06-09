package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/**
 * Clase que representa una posición dentro de un equipo, y puese ser asignada a log jugadores del mismo.
 */
@Entity
public class TeamPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonIgnore
    private Team team;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "position")
    @JsonIgnore
    private List<AssignedPosition> assignedpositions = new ArrayList<>();

    public TeamPosition(){}
    
    public TeamPosition(Team team, String name){
        this.team = team;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
