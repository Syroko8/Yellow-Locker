package com.example.nicolaspuebla_proyecto_final_android.data.model.dto

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team

/**
 * Clase que se emplea para recibir cierta información de un equipo.
 */
data class TeamExtended (
    val team: Team,
    val events: List<Event>
)