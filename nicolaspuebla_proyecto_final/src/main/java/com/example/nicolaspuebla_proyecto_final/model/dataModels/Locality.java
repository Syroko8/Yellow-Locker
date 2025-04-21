package com.example.nicolaspuebla_proyecto_final.model.dataModels;

import java.util.List;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Locality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;    
    @NonNull
    private String name;
    @OneToMany(mappedBy = "locality")
    @JsonBackReference("team-locality")
    private List<Team> teamsOnLocality;

    public Locality(){
        this.name = "default";
    }

    public Locality(String newName){
        this.name = newName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
