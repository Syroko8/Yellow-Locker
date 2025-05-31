package com.example.nicolaspuebla_proyecto_final_android.data.model.dto

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Locality

data class TeamCreation (
    val userId: Long,
    val teamName: String,
    val teamLocality: Locality,
    val teamSport: String
)