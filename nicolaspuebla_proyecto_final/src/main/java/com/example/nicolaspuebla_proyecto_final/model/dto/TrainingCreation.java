package com.example.nicolaspuebla_proyecto_final.model.dto;

/**
 * Clase que contiene la información necesaria para crear un entrenamiento.
 */
public class TrainingCreation extends EventCreation {

    public TrainingCreation(){}

    public TrainingCreation(String event_type, Long teamId, Double latitude, Double longitude, String date) {
        super(event_type, teamId, latitude, longitude, date);
    }
}
