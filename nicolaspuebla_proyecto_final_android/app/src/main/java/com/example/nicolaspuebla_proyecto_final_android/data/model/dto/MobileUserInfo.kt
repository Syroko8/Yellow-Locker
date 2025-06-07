package com.example.nicolaspuebla_proyecto_final_android.data.model.dto

/**
 * Clase que se emplea para recibir cierta información sobre un usuario del microservicio.
 */
data class MobileUserInfo (
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val disabled: Boolean = false,
    val birthDate: String,
    val age: Int
)