package com.example.nicolaspuebla_proyecto_final_android.data.model

data class Locality (
    val id: Long,
    val name: String,
    val teamsOnLocality: List<Long>
)
