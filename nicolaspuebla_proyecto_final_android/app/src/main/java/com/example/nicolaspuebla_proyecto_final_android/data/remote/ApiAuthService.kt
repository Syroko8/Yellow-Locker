package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginRequest
import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginResponse
import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.TokenCheckResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Interfaz que se intercambia información con el microservicio relacionada con la autenticación de usuario.
 */
interface ApiAuthService {

    /**
     * Función que realiza una petición de login.
     *
     * @param request Objeto que contiene la información necesaria para el login.
     */
    @POST("api/user/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    /**
     * Función que realiza una petición de logout.
     *
     * @param userId Identificador del usuario cuyo token se dará de baja.
     */
    @POST("api/user/logout")
    fun logout(@Query("userId") userId: Long): Call<String>

    /**
     * Función que realiza una petición de comprobación de validad de un token.
     *
     * @param userId Identificador del usuario al que pertenece el token.
     * @param token Token a comprobar.
     */
    @GET("api/token/token_check")
    fun tokenCheck(
        @Query("userId") userId: Long,
        @Query("token") token: String
    ): Call<TokenCheckResponse>
}