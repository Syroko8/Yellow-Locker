package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.TeamRolLevelResponse
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamRolPK
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TeamRolService {
    @POST("api/team_rol/level")
    fun getRolLevel(@Body teamRolPK: TeamRolPK): Call<TeamRolLevelResponse>
}