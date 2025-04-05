package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.TeamResponse
import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginRequest
import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginResponse
import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LogoutRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TeamService {

    @GET("/api/team/{id}")
    fun getTeam(@Path("id")id:Long): Call<TeamResponse>
}