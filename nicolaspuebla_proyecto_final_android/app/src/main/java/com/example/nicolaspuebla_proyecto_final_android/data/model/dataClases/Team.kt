package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

data class Team (
    val id: Long,
    val name: String,
    val locality: Locality,
    val logo: String,
    val chatKey: String,
    val sport: String,
    val positions: List<TeamPosition> = emptyList(),
    val messages: List<Message> = emptyList(),
    val teamRoles: List<TeamRol> = emptyList(),
    val eventList: List<Event> = emptyList(),
    val members: List<MobileUser> = emptyList()
)



