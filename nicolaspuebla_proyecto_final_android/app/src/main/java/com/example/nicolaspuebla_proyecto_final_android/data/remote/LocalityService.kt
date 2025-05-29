package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.LocalityListResponse
import retrofit2.Call
import retrofit2.http.GET

interface LocalityService {

    @GET("api/locality")
    fun getLocalities(): Call<LocalityListResponse>
}