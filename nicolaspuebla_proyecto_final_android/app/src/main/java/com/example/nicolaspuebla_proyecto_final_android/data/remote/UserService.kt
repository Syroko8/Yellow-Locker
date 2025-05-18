package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.UserSignUp
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @GET("/api/user/{id}")
    fun getUser(@Path("id")id:Long): Call<MobileUser>

    @GET("/api/user/team_users")
    fun getTeamUsers(@Body users:List<Long>): Call<List<User>>

    @POST("/api/user/signup_mobile")
    fun signUp(@Body user:UserSignUp): Call<MobileUser>
}