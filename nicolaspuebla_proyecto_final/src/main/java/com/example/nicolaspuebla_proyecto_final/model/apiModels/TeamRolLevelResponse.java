package com.example.nicolaspuebla_proyecto_final.model.apiModels;

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
