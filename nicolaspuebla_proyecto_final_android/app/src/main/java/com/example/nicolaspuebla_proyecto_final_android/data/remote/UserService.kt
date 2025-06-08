package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.UserSignUp
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Interfaz que se intercambia información con el microservicio relacionada con la entidad Team.
 */
interface UserService {

    /**
     * Función que realiza una petición para obtener la información de un usuario.
     *
     * @param id Identificador del usuario.
     */
    @GET("/api/user/{id}")
    fun getUser(@Path("id")id:Long): Call<MobileUser>

    /**
     * Función que realiza la petición de registro de un usuario.
     *
     * @param user Información de registro del usuario.
     * @return La información del nuevo usuario.
     */
    @POST("/api/user/signup_mobile")
    fun signUp(@Body user: UserSignUp): Call<MobileUser>
}