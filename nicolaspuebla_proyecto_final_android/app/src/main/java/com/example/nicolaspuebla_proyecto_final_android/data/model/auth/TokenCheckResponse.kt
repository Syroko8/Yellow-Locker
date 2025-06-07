package com.example.nicolaspuebla_proyecto_final_android.data.model.auth

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser

/**
 * Clase empleada para recibir la respuesta del microsercivio tras comprobar la valided de un token.
 */
data class TokenCheckResponse (
    val user: MobileUser?
)