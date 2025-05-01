package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

import java.sql.Date

open class Event(
    open val id: Long,
    open val team: Team,
    open val address: String,
    open val latitude: Int,
    open val longitude: Int,
    open val date: String
)