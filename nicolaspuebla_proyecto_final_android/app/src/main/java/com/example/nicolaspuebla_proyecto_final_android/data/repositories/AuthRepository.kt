package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginRequest
import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginResponse
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository {

    suspend fun login(email: String, password: String): LoginResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val call = RetrofitInstance.yellowLockerAuth.login(LoginRequest(email, password))

                val response = call.execute()

                if (response.isSuccessful) {
                    response.body()
                } else {
                    println("Login failed with code: ${response.code()} and message: ${response.message()}")
                    null
                }
            } catch (e: Exception) {
                println("Login request failed: ${e.message}")
                null
            }
        }
    }
}