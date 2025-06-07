package com.example.nicolaspuebla_proyecto_final_android.data.model.auth

/**
 * Clase empleada para realizar peticiones de login.
 */
data class LoginRequest(
    val email: String,
    val passwd: String
)