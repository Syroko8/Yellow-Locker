package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.MatchReciever
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.TrainingReciever
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TeamEventService {

    @GET("api/event/team_matches/{id}")
    fun getTeamMatches(@Path("id")id: Long): Call<List<MatchReciever>>

    @GET("api/event/team_trainings/{id}")
    fun getTeamTrainings(@Path("id")id: Long): Call<List<TrainingReciever>>

    @PUT("api/event/update")
    fun updateEvent(@Body event: Event, @Query(value = "opponentId", encoded = true) opponentId: Long?): Call<Event>

    @DELETE("api/event/delete")
    fun deleteEvent(@Body event: Event): Call<Event>
}