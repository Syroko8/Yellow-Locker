package com.example.nicolaspuebla_proyecto_final_android.data.remote

import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Objeto en el que gestiona el envío de las peticiones al microservicio.
 */
object RetrofitInstance {
    private const val BASE_URL = "http://35.179.126.83:8080"

    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS) // Tiempo de conexión
        .readTimeout(30, TimeUnit.SECONDS) // Tiempo de lectura
        .writeTimeout(15, TimeUnit.SECONDS) // Tiempo de escritura
        .addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            val token = SessionManager.bearerToken
            if (token != null) {
                requestBuilder.addHeader("Authorization", "Bearer $token")
            }
            chain.proceed(requestBuilder.build())
        }.build()

    val yellowLockerAuth: ApiAuthService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiAuthService::class.java)
    }

    val yellowLockerTeamService: TeamService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(TeamService::class.java)
    }

    val yellowLockerUserService: UserService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UserService::class.java)
    }

    val yellowLockerTeamRolService: TeamRolService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(TeamRolService::class.java)
    }

    val yellowLockerTeamEventService: TeamEventService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(TeamEventService::class.java)
    }

    val yellowLockerLocalityService: LocalityService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(LocalityService::class.java)
    }

    val yellowLockerTeamPositionService: TeamPositionService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(TeamPositionService::class.java)
    }

    val yellowLockerAssignedPositionService: AssignedPositionService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(AssignedPositionService::class.java)
    }
}