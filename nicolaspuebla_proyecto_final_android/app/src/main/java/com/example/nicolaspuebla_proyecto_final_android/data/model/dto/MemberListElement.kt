package com.example.nicolaspuebla_proyecto_final_android.data.model.dto

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser

/**
 * Clase que forma parte de una lista de miembros de un equipo junto con su rol en el mismo.
 */
data class MemberListElement (
    val rolType: String,
    val user: MobileUser
)