package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

data class Training(
    override val event_type: String,
    override val id: Long,
    override val team: Team,
    override val latitude: Double,
    override val longitude: Double,
    override val date: String,
) : Event(event_type, id, team, latitude, longitude, date)