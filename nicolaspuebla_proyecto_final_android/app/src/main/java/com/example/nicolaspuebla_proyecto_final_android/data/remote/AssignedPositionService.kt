package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.AssignedPosition
import retrofit2.Call
import retrofit2.http.PUT
import retrofit2.http.Query

interface AssignedPositionService {

    @PUT("api/assigned_position/change")
    fun changeAssignedPosition(
        @Query("userId") userId: Long,
        @Query("teamId") teamId: Long,
        @Query("teamPositionId") teamPositionId: Long
    ): Call<AssignedPosition>
}