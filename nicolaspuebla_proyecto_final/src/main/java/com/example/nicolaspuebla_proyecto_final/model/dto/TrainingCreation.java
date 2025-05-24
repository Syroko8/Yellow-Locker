package com.example.nicolaspuebla_proyecto_final.model.dto;

public class TrainingCreation extends EventCreation {

    public TrainingCreation(){}

    public TrainingCreation(String event_type, Long teamId, Double latitude, Double longitude, String date) {
        super(event_type, teamId, latitude, longitude, date);
    }
}
