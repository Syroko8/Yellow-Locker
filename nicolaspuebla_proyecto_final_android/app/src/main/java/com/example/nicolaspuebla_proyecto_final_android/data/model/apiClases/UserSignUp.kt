package com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases

data class UserSignUp(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val disabled: Boolean = false,
    val messages: List<Long> = emptyList(),
    val notifications: List<Long> = emptyList(),
    val teamRoles: List<Long> = emptyList()
)
