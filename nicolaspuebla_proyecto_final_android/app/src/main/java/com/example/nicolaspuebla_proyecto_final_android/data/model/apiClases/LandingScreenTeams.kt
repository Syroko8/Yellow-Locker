package com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Locality
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamPositions
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamRol
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.User

data class LandingScreenTeams (
    val id: Long,
    val name: String,
    val locality: Locality,
    val logo: String,
    val sport: String,
)