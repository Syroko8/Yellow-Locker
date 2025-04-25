package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.MessageId
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class MobileUser (
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val disabled: Boolean = false,
    val birthDate: String,
    val age: Int,
    val teamList: List<Team> = emptyList(),
    val messages: List<MessageId> = emptyList(),
    val teamRoles: List<TeamRol> = emptyList(),
    val asignedPositions: List<AssignedPosition> = emptyList()
)
