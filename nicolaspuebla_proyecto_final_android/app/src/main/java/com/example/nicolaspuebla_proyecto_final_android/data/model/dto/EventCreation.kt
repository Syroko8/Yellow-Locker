package com.example.nicolaspuebla_proyecto_final_android.data.model.dto

open class EventCreation (
    open val event_type: String,
    open val teamId: Long,
    open val latitude: Double,
    open val longitude: Double,
    open val date: String,
)