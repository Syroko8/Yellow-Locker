package com.example.nicolaspuebla_proyecto_final.model.dto;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.Locality;

/**
 * Clase que contiene la información necesaria para la representación de los equipos de la pantalla de inicio de la aplicación.
 */
public class LandingScreenTeams {
    private Long id;
    private String name;
    private Locality locality;
    private String logo;
    private String sport;

    public LandingScreenTeams(){}

    public LandingScreenTeams(Long id, String name, Locality locality, String logo, String sport) {
        this.id = id;
        this.name = name;
        this.locality = locality;
        this.logo = logo;
        this.sport = sport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}
