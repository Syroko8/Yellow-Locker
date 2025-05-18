package com.example.nicolaspuebla_proyecto_final_android.data.model.dto

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Locality
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamPosition
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamRol
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.User

data class TeamResponse (
    val id: Long,
    val name: String,
    val locality: Locality,
    val logo: String,
    val chatKey: String,
    val sport: String,
    val positions: List<TeamPosition>,
    val messages: List<Long>,
    val teamRoles: List<TeamRol>,
    val eventList: List<Event>,
    val members: List<User>
)