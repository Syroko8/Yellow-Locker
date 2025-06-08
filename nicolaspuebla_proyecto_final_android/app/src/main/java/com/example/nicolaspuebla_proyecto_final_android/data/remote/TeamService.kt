package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.MemberListElement
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.TeamCreation
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.TeamNameListReciever
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz que se intercambia información con el microservicio relacionada con la entidad Team.
 */
interface TeamService {

    /**
     * Función que realiza una petición para obtener la información de un equipo.
     *
     * @param id Identificador del equipo.
     */
    @GET("/api/team/{id}")
    fun getTeam(@Path("id")id:Long): Call<Team>

    /**
     * Función que realiza una petición para obtener todos los equipos.
     *
     * @return Una lista con todos los equipos.
     */
    @GET("api/team")
    fun getAllTeams(): Call<List<Team>>

    /**
     * Función que realiza una petición de unión de un usuario a un equipo.
     *
     * @param teamId Identificador del equipo.
     * @param userId Identificador del usuario.
     * @return la información del usuario.
     */
    @POST("api/team/join")
    fun joinTeam(@Query("teamId")teamId: Long, @Query("userId")userId: Long): Call<MobileUser>

    /**
     * Función que realiza una petición para recibir una lista formada por los miembros de un equipo.
     *
     * @param id Identificador del equipo.
     * @return Una lista con los miembros del equipo y sus roles en él.
     */
    @GET("api/team/members/{id}")
    fun getMembers(@Path("id")id: Long): Call<List<MemberListElement>>

    /**
     * Función que realiza la petición de creación de un equipo.
     *
     * @param teamCreation Información de creación del equipo.
     * @return La información del equipo.
     */
    @POST("api/team/create")
    fun createTeam(@Body teamCreation: TeamCreation): Call<Team>

    /**
     * Función que realiza la petición de abandono de un equipo.
     *
     * @param userId Identificador del usuario.
     * @param teamId Identificador del equipo.
     * @return La información del equipo.
     */
    @POST("api/team/leave")
    fun leaveTeam(
        @Query("userId") userId: Long,
        @Query("teamId") teamId: Long
    ): Call<Team>
}