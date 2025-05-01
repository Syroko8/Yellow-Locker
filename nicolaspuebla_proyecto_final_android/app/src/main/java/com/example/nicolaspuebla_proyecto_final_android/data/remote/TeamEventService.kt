package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.MatchReciever
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamEventService {
    @GET("api/event/team_matches/{id}")
    fun getTeamMatches(@Path("id")id: Long): Call<List<MatchReciever>>
}