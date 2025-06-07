package com.example.nicolaspuebla_proyecto_final_android.data.model.auth

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser

/**
 * Clase empleada para recibir la respuesta del microservicio tras un login.
 */
data class LoginResponse (
    val user: MobileUser,
    val token: String
)