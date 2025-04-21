package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.TeamInfo
import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.TeamResponse
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TeamService {

    @GET("/api/team/{id}")
    fun getTeam(@Path("id")id:Long): Call<TeamResponse>

    @GET("api/team")
    fun getAllTeams(): Call<List<TeamInfo>>

    @POST("api/team/join")
    fun joinTeam(@Query("teamId")teamId: Long, @Query("userId")userId: Long): Call<MobileUser>
}