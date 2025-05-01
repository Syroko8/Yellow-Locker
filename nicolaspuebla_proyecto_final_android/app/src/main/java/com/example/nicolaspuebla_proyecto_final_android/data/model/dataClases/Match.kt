package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

import java.sql.Date

class Match(
    override val id: Long,
    override val team: Team,
    override val address: String,
    override val latitude: Int,
    override val longitude: Int,
    override val date: String,
    val oponent: Team,
    val own_goals: Int,
    val oponent_goals: Int
) : Event(id, team, address, latitude, longitude, date)

