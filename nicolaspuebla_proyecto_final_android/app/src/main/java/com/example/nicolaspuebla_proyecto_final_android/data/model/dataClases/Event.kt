package com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases

import java.sql.Date

open class Event(
    open val id: Long? = null,
    open val teamId: Long? = null,
    open val address: Long? = null,
    open val latitude: Int? = null,
    open val longitude: Int? = null,
    open val date: Date? = null
)