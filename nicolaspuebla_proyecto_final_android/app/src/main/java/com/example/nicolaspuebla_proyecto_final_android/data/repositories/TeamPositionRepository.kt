package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamPosition
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.SQLException
import java.sql.SQLIntegrityConstraintViolationException

class TeamPositionRepository {

    suspend fun createTeamPosition(teamId: Long, name: String): TeamPosition?{
        return withContext((Dispatchers.IO)){
            try {
                val call = RetrofitInstance.yellowLockerTeamPositionService.createTeamPosition(teamId, name)
                val response = call.execute()

                if(response.isSuccessful){
                    response.body()
                } else if(response.code() == 401){
                    throw Exception("401")
                } else {
                    throw Exception("Internal server error")
                }
            }catch (e: Exception){
                throw Exception(e.message)
            }
        }
    }

    suspend fun deleteTeamPosition(teamPositionId: Long): Long?{
        return withContext((Dispatchers.IO)){
            try {
                val call = RetrofitInstance.yellowLockerTeamPositionService.deleteTeamPosition(teamPositionId)
                val response = call.execute()

                if(response.isSuccessful){
                    response.body()
                } else if(response.code() == 401){
                    throw Exception("401")
                } else if(response.code() == 409){
                    throw Exception("409")
                } else {
                    throw Exception("Internal server error")
                }
            }catch (e: Exception){
                throw Exception(e.message)
            }
        }
    }

    suspend fun getTeamPositions(teamId: Long): List<TeamPosition>? {
        return withContext((Dispatchers.IO)){
            try {
                val call = RetrofitInstance.yellowLockerTeamPositionService.getTeamPositions(teamId)
                val response = call.execute()

                if(response.isSuccessful){
                    response.body()
                } else if(response.code() == 401){
                    throw Exception("401")
                } else {
                    throw Exception("Internal server error")
                }
            }catch (e: Exception){
                throw Exception(e.message)
            }
        }
    }
}