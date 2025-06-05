package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.MemberListElement
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.TeamNameListReciever
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.TeamCreation
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.TeamExtended
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TeamService {

    @GET("/api/team/{id}")
    fun getTeam(@Path("id")id:Long): Call<Team>

    @GET("api/team")
    fun getAllTeams(): Call<List<Team>>

    @POST("api/team/join")
    fun joinTeam(@Query("teamId")teamId: Long, @Query("userId")userId: Long): Call<MobileUser>


    @GET("api/team/members/{id}")
    fun getMembers(@Path("id")id: Long): Call<List<MemberListElement>>

    @GET("/api/team/names")
    fun getTeamNames(): Call<TeamNameListReciever>

    @POST("api/team/create")
    fun createTeam(@Body teamCreation: TeamCreation): Call<Team>

    @POST("api/team/leave")
    fun leaveTeam(
        @Query("userId") userId: Long,
        @Query("teamId") teamId: Long
    ): Call<Team>
}