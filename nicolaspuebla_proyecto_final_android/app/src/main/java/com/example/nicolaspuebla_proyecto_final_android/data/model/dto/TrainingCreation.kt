package com.example.nicolaspuebla_proyecto_final_android.data.model.dto

/**
 * Clase empleada en la petición para la creación de un entrenamiento.
 */
data class TrainingCreation (
    override val event_type: String = "match",
    override val teamId: Long,
    override val latitude: Double,
    override val longitude: Double,
    override val date: String
): EventCreation(event_type, teamId, latitude, longitude, date)
