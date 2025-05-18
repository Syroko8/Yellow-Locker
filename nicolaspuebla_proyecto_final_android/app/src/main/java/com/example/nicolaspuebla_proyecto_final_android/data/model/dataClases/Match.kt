package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

import java.sql.Date

data class Match(
    override val event_type: String,
    override val id: Long,
    override val team: Team,
    override val address: String,
    override val latitude: Double,
    override val longitude: Double,
    override val date: String,
    val opponent: Team,
    val own_goals: Int,
    val opponent_goals: Int
) : Event(event_type, id, team, address, latitude, longitude, date)

