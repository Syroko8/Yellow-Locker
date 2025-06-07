package com.example.nicolaspuebla_proyecto_final_android.data.model.dto

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Locality

/**
 * Clase que se utiliza para recibir una lista de localidades del microservicio.
 */
data class LocalityListResponse (
    val localities: List<Locality>
)