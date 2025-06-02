package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

import com.google.gson.annotations.SerializedName

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
    @SerializedName("eventList")
    val eventList: List<Event> = emptyList(),
    val members: List<MobileUser> = emptyList(),
    val assignedPositions: List<AssignedPosition> = emptyList()
)



