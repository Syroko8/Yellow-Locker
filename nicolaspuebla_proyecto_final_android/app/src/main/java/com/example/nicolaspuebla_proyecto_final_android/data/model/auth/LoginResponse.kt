package com.example.nicolaspuebla_proyecto_final_android.data.model.auth

import com.example.nicolaspuebla_proyecto_final_android.data.model.User

data class LoginResponse (
    val token: String,
    val user: User
)