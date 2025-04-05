package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

import java.sql.Date

class Match(
    override val id: Long? = null,
    override val teamId: Long? = null,
    override val address: Long? = null,
    override val latitude: Int? = null,
    override val longitude: Int? = null,
    override val date: Date? = null,
    val oponent: String? = null,
    val ownGoals: Int? = null,
    val oponentGoals: Int? = null
) : Event(id, teamId, address, latitude, longitude, date)