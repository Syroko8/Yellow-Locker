package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamRol
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamRolPK
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import com.example.nicolaspuebla_proyecto_final_android.utils.TeamRoles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamRolRepository {

    suspend fun getRolLevel(teamRolPK: TeamRolPK): TeamRoles{
        return withContext(Dispatchers.IO){
            try {
                val call = RetrofitInstance.yellowLockerTeamRolService.getRolLevel(teamRolPK)
                val response = call.execute()
                if(response.isSuccessful){
                    when(response.body()?.level){
                        "Player" -> TeamRoles.Player
                        "Coach" -> TeamRoles.Coach
                        else -> TeamRoles.Captain
                    }
                } else if(response.code() == 401){
                    throw Exception("401")
                } else {
                    throw Exception("Internal server error")
                }
            } catch (e:Exception){
                throw Exception(e.message)
            }
        }
    }

    suspend fun changeRol(userId: Long, teamId: Long, rolType: TeamRoles): TeamRol?{
        return withContext(Dispatchers.IO){
            try {
                val call = RetrofitInstance.yellowLockerTeamRolService.changeUserRol(userId, teamId, rolType)
                val response = call.execute()
                if(response.isSuccessful){
                    response.body()
                } else if(response.code() == 401){
                    throw Exception("401")
                } else {
                    throw Exception("Internal server error")
                }
            } catch (e:Exception){
                throw Exception(e.message)
            }
        }
    }
}