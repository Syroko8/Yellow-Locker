package com.example.nicolaspuebla_proyecto_final_android.data.model.dto

/**
 * Clase empleada en la petición de registro de un usuario.
 */
data class UserSignUp(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val disabled: Boolean = false,
    val birthDate: String
)
