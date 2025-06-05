package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamPosition
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface  TeamPositionService {

    @POST("api/team_position/create")
    fun createTeamPosition(
        @Query("teamId") teamId: Long,
        @Query("name") name: String
    ): Call<TeamPosition>

    @POST("api/team_position/delete")
    fun deleteTeamPosition(@Query("teamPositionId") teamPositionId: Long): Call<Long>

    @GET("api/team_position/{teamId}")
    fun getTeamPositions(@Path("teamId") teamId: Long): Call<List<TeamPosition>>

}