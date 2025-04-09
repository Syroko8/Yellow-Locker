package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.UserSignUp
import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginRequest
import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginResponse
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.User
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {

    suspend fun getUser(id: Long): User?{
        return withContext(Dispatchers.IO) {
            try {
                val call = RetrofitInstance.yellowLockerUserService.getUser(id)
                val response = call.execute()

                if (response.isSuccessful) {
                    response.body()
                } else {
                    println("Fetch failed with code: ${response.code()} and message: ${response.message()}")
                    null
                }
            } catch (e: Exception) {
                println("Fetch request failed: ${e.message}")
                null
            }
        }
    }

    suspend fun signUp(user: UserSignUp): User?{
        return withContext(Dispatchers.IO) {
            try {
                val call = RetrofitInstance.yellowLockerUserService.signUp(user)
                val response = call.execute()

                if (response.isSuccessful) {
                    response.body()
                } else {
                    println("SignUp failed with code: ${response.code()} and message: ${response.message()}")
                    null
                }
            } catch (e: Exception) {
                println("SignUp request failed: ${e.message}")
                null
            }
        }
    }
}