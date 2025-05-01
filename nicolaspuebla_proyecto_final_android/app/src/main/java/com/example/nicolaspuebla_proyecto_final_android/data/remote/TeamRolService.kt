package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.MemberListElement
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamRolPK
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamRolService {
    @GET("api/team_rol/level")
    fun getRolLevel(@Body teamRolPK: TeamRolPK): Call<String>
}