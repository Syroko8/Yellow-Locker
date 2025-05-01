package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

import java.sql.Date

class Training(
    override val id: Long,
    override val team: Team,
    override val address: String,
    override val latitude: Int,
    override val longitude: Int,
    override val date: String,
) : Event(id, team, address, latitude, longitude, date)