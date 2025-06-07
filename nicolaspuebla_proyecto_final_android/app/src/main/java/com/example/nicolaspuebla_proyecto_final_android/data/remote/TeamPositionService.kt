package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamPosition
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz que se intercambia información con el microservicio relacionada con la entidad TeamPosition.
 */
interface TeamPositionService {

    /**
     * Función que realiza una peticón de creación de una posición.
     *
     * @param teamId Identificador del equipo.
     * @param name Nombre para la nueva posición.
     */
    @POST("api/team_position/create")
    fun createTeamPosition(
        @Query("teamId") teamId: Long,
        @Query("name") name: String
    ): Call<TeamPosition>

    /**
     * Función que realiza una petición para la eliminación de una posción.
     *
     * @param teamPositionId Identificador de la posición a eliminar.
     */
    @POST("api/team_position/delete")
    fun deleteTeamPosition(@Query("teamPositionId") teamPositionId: Long): Call<Long>

    /**
     * Función que realiza una petición para recibir las posiciones de un equipo.
     *
     * @param teamId Identificador del equipo.
     */
    @GET("api/team_position/{teamId}")
    fun getTeamPositions(@Path("teamId") teamId: Long): Call<List<TeamPosition>>

}