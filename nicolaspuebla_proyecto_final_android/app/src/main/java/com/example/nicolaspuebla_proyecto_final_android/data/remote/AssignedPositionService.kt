package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.AssignedPosition
import retrofit2.Call
import retrofit2.http.PUT
import retrofit2.http.Query

/**
 * Interfaz que se intercambia información con el microservicio relacionada con la entidad AssignedPosition.
 *
 */
interface AssignedPositionService {

    /**
     * Función que realiza una peticón de cambio de la posición asignada a un usuario en un equipo.
     *
     * @param userId Identificador del usuario al que pertenece la posición.
     * @param teamId Identificador del equipo donde está signada la posición.
     * @param teamPositionId Identificador de la posición existente en el equipo.
     */
    @PUT("api/assigned_position/change")
    fun changeAssignedPosition(
        @Query("userId") userId: Long,
        @Query("teamId") teamId: Long,
        @Query("teamPositionId") teamPositionId: Long
    ): Call<AssignedPosition>
}