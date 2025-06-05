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

interface TeamRolService {
    @POST("api/team_rol/level")
    fun getRolLevel(@Body teamRolPK: TeamRolPK): Call<TeamRolLevelResponse>

    @PUT("api/team_rol/change")
    fun changeUserRol(
        @Query("userId") userId: Long,
        @Query("teamId") teamId: Long,
        @Query("rolType") rolType: TeamRoles
    ): Call<TeamRol>
}