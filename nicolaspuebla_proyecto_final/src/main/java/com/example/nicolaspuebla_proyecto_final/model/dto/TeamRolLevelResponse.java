package com.example.nicolaspuebla_proyecto_final.model.dto;

/**
 * Clase que contiene la información de respuesta a la petición de comprobación del nivel de un rol de equipo.
 */
public class TeamRolLevelResponse {
    String level;

    public TeamRolLevelResponse(){}

    public TeamRolLevelResponse(String level){
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
