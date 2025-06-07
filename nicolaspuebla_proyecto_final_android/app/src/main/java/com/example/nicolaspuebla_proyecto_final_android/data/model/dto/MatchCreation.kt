package com.example.nicolaspuebla_proyecto_final_android.data.model.dto

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Clase que se emplea en la petición de creación de un equipo.
 */
data class MatchCreation (
    override val event_type: String = "match",
    override val teamId: Long,
    override val latitude: Double,
    override val longitude: Double,
    override val date: String,
    val opponent: Team,
    @JsonProperty("own_goals")
    val ownGoals: Int,
    @JsonProperty("opponent_goals")
    val opponentGoals: Int
): EventCreation(event_type, teamId, latitude, longitude, date)