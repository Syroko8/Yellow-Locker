package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.TeamResponse
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamRepository {

    suspend fun getTeam(id: Long): TeamResponse?{
        return withContext(Dispatchers.IO){
            try {
                val call = RetrofitInstance.yellowLockerTeamService.getTeam(id)
                val response = call.execute()

                if(response.isSuccessful){
                    response.body()
                } else {
                    println("Fetch failed with code: ${response.code()} and message: ${response.message()}")
                    null
                }
            } catch (e: Exception){
                println("Fetch request failed: ${e.message}")
                null
            }
        }
    }
}