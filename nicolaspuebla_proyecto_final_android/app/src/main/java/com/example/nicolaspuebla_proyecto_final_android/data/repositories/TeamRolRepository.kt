package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamRol
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamRolPK
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import com.example.nicolaspuebla_proyecto_final_android.utils.TeamRoles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repositorio a través del cual los view models pueden ejecutar las peticiones necesarias al microservicio sobre la entidad TeamRol.
 */
class TeamRolRepository {

    /**
     * Función que obtiene el tipo de rol de un usuario en un equipo.
     *
     * @param teamRolPK Identificador del rol.
     * @return Tipo de rol poseído por el usuario.
     */
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

    /**
     * Función que ejecuta el cambio de rol para un usuario en un equipo a través del servicio.
     *
     * @param userId Identificador del usuario.
     * @param teamId Identificador del equipo.
     * @param rolType Tipo del nuevo rol a asignar.
     * @return Información sobre el rol asignado.
     */
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