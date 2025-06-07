package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

/**
 * Clase que almacena la información de una posición asignada en un equipo a un usuario.
 */
data class AssignedPosition (
    val id: AssignedPositionPk,
    val position: TeamPosition
)