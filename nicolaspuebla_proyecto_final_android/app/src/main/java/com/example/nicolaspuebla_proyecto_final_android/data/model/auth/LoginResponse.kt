package com.example.nicolaspuebla_proyecto_final_android.data.model.auth

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser

data class LoginResponse (
    val user: MobileUser,
    val token: String
)