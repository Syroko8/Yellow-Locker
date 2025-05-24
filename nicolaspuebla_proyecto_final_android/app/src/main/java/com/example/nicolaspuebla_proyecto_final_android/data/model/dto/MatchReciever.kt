package com.example.nicolaspuebla_proyecto_final_android.data.model.dto

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team

data class MatchReceiver (
    val event_type: String,
    val id: Long,
    val team: Team,
    val latitude: Double,
    val longitude: Double,
    val date: String,
    val opponent: Team,
    val opponentGoals: Int,
    val ownGoals: Int
)