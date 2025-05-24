package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.EventCreation
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.MatchReceiver
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.TrainingReceiver
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TeamEventService {

    @GET("api/event/team_matches/{id}")
    fun getTeamMatches(@Path("id")id: Long): Call<List<MatchReceiver>>

    @GET("api/event/team_trainings/{id}")
    fun getTeamTrainings(@Path("id")id: Long): Call<List<TrainingReceiver>>

    @PUT("api/event/update")
    fun updateEvent(
        @Body event: Event,
        @Query(value = "opponentId") opponentId: Long?,
        @Query("ownGoals") ownGoals: Int?,
        @Query("opponentGoals") opponentGoals: Int?
    ): Call<Event>

    @DELETE("api/event/delete/{id}")
    fun deleteEvent(@Path("id")eventId: Long): Call<Long>

    @POST("api/event/create")
    fun createEvent(
        @Body newEvent: EventCreation,
        @Query("opponentId") opponentId: Long?
    ): Call<Event>
}