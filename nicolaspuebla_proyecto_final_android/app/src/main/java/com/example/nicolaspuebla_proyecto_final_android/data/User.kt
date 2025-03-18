package com.example.nicolaspuebla_proyecto_final_android.data

data class User(
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val disabled: Boolean,
    val messages: List<Long>,
    val notifications: List<Long>,
    val teamRoles: List<Long>,
    val token: Long
)
