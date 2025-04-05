package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

data class Team (
    val id: Long,
    val name: String,
    val locality: Long,
    val logo: String,
    val chatKey: String,
    val sport: String,
    val positions: List<Long>,
    val messages: List<Long>,
    val teamRoles: List<Long>,
    val eventList: List<Long>,
    val members: List<Long>
)



