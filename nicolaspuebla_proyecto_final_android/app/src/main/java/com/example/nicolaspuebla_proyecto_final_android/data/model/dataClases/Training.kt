package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

import java.sql.Date

class Training(
    override val id: Long,
    override  val teamId: Long,
    override  val address: Long,
    override  val latitude: Int,
    override  val longitude: Int,
    override  val date: Date
) : Event(id, teamId, address, latitude, longitude, date)