package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.LocalityListResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Interfaz que se intercambia información con el microservicio relacionada con la entidad Locality.
 */
interface LocalityService {

    /**
     * Función que envía una petición para obtener todas las localidades españolas.
     */
    @GET("api/locality")
    fun getLocalities(): Call<LocalityListResponse>
}