package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

import com.fasterxml.jackson.annotation.JsonProperty

data class Match(
    override val event_type: String,
    override val id: Long,
    override val team: Team,
    override val latitude: Double,
    override val longitude: Double,
    override val date: String,
    val opponent: Team,
    @JsonProperty("own_goals")
    val ownGoals: Int,
    @JsonProperty("opponent_goals")
    val opponentGoals: Int
) : Event(event_type, id, team, latitude, longitude, date)

