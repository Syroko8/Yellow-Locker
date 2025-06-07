package com.example.nicolaspuebla_proyecto_final_android.data.model.dto

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team

/**
 * Clase utilizada para recibir información sobre un entrenamiento.
 */
data class TrainingReceiver (
    val event_type: String,
    val id: Long,
    val team: Team,
    val latitude: Double,
    val longitude: Double,
    val date: String,
)