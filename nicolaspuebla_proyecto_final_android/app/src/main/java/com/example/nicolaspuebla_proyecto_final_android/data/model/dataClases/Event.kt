package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

open class Event(
    open val event_type: String,
    open val id: Long,
    open val team: Team,
    open val latitude: Double,
    open val longitude: Double,
    open val date: String
)