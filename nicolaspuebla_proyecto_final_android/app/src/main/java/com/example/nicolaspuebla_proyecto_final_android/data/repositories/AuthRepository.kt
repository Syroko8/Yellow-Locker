package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginRequest
import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginResponse
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository {

    @Throws(Exception::class)
    suspend fun login(request: LoginRequest): LoginResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val call = RetrofitInstance.yellowLockerAuth.login(request)
                val response = call.execute()

                if (response.isSuccessful) {
                    response.body()
                } else if(response.code() == 401){
                    throw Exception("401")
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

    suspend fun logout(token: String = SessionManager.bearerToken?:""): Unit{
        return withContext(Dispatchers.IO){
            try{
                val call = token.let { RetrofitInstance.yellowLockerAuth.logout(token)}
                val response = call.execute()

                if (response.isSuccessful){
                    response.body()
                } else{
                    println("Logout failed with code: ${response.code()} and message: ${response.message()}")
                    throw Exception(response.message())
                }
            } catch (e: Exception) {
                println("Logout request failed: ${e.message}")
                throw Exception(e.message)
            }
        }
    }

    suspend fun checkToken(userId: Long, token: String): MobileUser?{
        return withContext(Dispatchers.IO){
            try{
                val call = RetrofitInstance.yellowLockerAuth.tokenCheck(userId, token)
                val response = call.execute()

                if (response.isSuccessful){
                    response.body()?.user
                } else{
                    println(response.message())
                    throw Exception(response.message())
                }
            } catch (e: Exception) {
                println("Logout request failed: ${e.message}")
                throw Exception(e.message)
            }
        }
    }
}