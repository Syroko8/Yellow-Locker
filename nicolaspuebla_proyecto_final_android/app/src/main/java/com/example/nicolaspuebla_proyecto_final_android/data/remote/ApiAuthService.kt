package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginRequest
import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginResponse
import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LogoutRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiAuthService {

    @POST("api/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("api/logout")
    fun logout(@Body request: LogoutRequest): Call<LoginResponse>

    
}