package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import androidx.compose.material3.rememberTooltipState
import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.MobileUserInfo
import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.TeamInfo
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamRepository {

    suspend fun getTeam(id: Long): Any?{
        return withContext(Dispatchers.IO){
            try {
                val call = RetrofitInstance.yellowLockerTeamService.getTeam(id)
                val response = call.execute()

                if(response.isSuccessful){
                    response.body()
                } else if(response.code() == 401) {
                    "401"
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

    @Throws(Exception::class)
    suspend  fun getAllTeams(): List<TeamInfo>? {
        return withContext(Dispatchers.IO){
            try {
                val call = RetrofitInstance.yellowLockerTeamService.getAllTeams()
                val response = call.execute()

                if(response.isSuccessful){
                    println(">>>>>>>>>>>>>>>>>>>>>>> ${response.body()}")
                    response.body()
                } else if(response.code() == 401) {
                    throw Exception("401")
                } else {
                    println("Fetch failed with code: ${response.code()} and message: ${response.message()}")
                    throw Exception("Internal server error")
                }
            } catch (e: Exception){
                println("Fetch request failed: ${e.message}")
                throw Exception(e.message)
            }
        }
    }

    @Throws(Exception::class)
    suspend fun joinTeam(teamId: Long, userId: Long): MobileUser? {
        return  withContext(Dispatchers.IO){
            try {
                println(">>>>>>>>>>>>>>>>>>>>>>>${teamId} - ${userId}")
                val call = RetrofitInstance.yellowLockerTeamService.joinTeam(teamId, userId)
                val response = call.execute()

                if(response.isSuccessful){
                    response.body()
                } else if(response.code() == 401){
                    throw Exception("401")
                } else {
                    println("${response.code()}: ${response.message()}")
                    throw Exception("Internal server error.")
                }
            } catch (e:Exception){
                println("${e.message}")
                throw Exception(e.message)
            }
        }
    }
}