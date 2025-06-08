package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamRol
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.TeamRolLevelResponse
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamRolPK
import com.example.nicolaspuebla_proyecto_final_android.utils.TeamRoles
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz que se intercambia información con el microservicio relacionada con la entidad TeamRol.
 *
 */
interface TeamRolService {

    /**
     * Función que realiza una petición para recibir el nivel de rol de cierta posición.
     *
     * @param teamRolPK Identificador de la asignación del rol.
     * @return La información sobre el nivel de rol del usuario.
     */
    @POST("api/team_rol/level")
    fun getRolLevel(@Body teamRolPK: TeamRolPK): Call<TeamRolLevelResponse>

    /**
     * Función que realiza un apetición para actualizar el rol de un miembro de un equipo.
     *
     * @param userId Identificador del usuario.
     * @param teamId Identificador del equipo al que pertenece el usuario.
     * @param rolType Nuevo rol a asignar.
     * @return La unformación sobre el nuevo rol.
     */
    @PUT("api/team_rol/change")
    fun changeUserRol(
        @Query("userId") userId: Long,
        @Query("teamId") teamId: Long,
        @Query("rolType") rolType: TeamRoles
    ): Call<TeamRol>
}