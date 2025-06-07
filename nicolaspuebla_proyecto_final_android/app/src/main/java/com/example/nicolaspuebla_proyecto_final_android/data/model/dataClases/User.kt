package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Clase que almacena la información de un usuario.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
open class User(
    open val id: Long,
    open val name: String,
    open val surname: String,
    open val email: String,
    open val password: String,
    open val disabled: Boolean = false,
    open val notifications: List<Long> = emptyList(),
)
