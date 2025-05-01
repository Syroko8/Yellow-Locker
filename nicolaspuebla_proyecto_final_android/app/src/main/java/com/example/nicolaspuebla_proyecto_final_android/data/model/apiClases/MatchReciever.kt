package com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team

data class MatchReciever (
    val id: Long,
    val team: Team,
    val address: String,
    val latitude: Int,
    val longitude: Int,
    val date: String,
    val oponent: Team,
    val own_goals: Int,
    val oponent_goals: Int
)