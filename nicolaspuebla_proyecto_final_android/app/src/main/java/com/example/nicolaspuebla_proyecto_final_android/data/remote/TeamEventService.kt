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

/**
 * Interfaz que se intercambia información con el microservicio relacionada con la entidad TeamEvent.
 */
interface TeamEventService {

    /**
     * Función que envía una petición para recibir los partidos de un equipo.
     *
     * @param id Identificador del equipo.
     */
    @GET("api/event/team_matches/{id}")
    fun getTeamMatches(@Path("id")id: Long): Call<List<MatchReceiver>>

    /**
     * Función que envía una petición para revibir los entrenamientos de un equipo.
     *
     * @param id Identificador del equipo.
     */
    @GET("api/event/team_trainings/{id}")
    fun getTeamTrainings(@Path("id")id: Long): Call<List<TrainingReceiver>>

    /**
     * Función que envía una petición para actualizar la información de un evento.
     *
     * @param event Evento a actualizar.
     * @param opponentId Identificador del oponente.
     * @param ownGoals Número de goles propios.
     * @param opponentGoals Número de goles del oponente.
     */
    @PUT("api/event/update")
    fun updateEvent(
        @Body event: Event,
        @Query(value = "opponentId") opponentId: Long?,
        @Query("ownGoals") ownGoals: Int?,
        @Query("opponentGoals") opponentGoals: Int?
    ): Call<Event>

    /**
     * Función que envía una petición de eliminación de un evento.
     *
     * @param eventId Identificador del evento a eliminar.
     */
    @DELETE("api/event/delete/{id}")
    fun deleteEvent(@Path("id")eventId: Long): Call<Long>

    /**
     * Función que envía una petición de creación de un evento.
     *
     * @param newEvent Información de evento.
     * @param opponentId Identificador del oponente en caso de ser un partido.
     */
    @POST("api/event/create")
    fun createEvent(
        @Body newEvent: EventCreation,
        @Query("opponentId") opponentId: Long?
    ): Call<Event>
}