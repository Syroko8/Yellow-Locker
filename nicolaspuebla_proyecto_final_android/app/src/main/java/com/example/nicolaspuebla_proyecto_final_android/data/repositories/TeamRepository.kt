package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.MemberListElement
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.TeamCreation
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repositorio a través del cual los view models pueden ejecutar las peticiones necesarias al microservicio sobre la entidad Team.
 */
class TeamRepository {

    /**
     * Función que obtiene la información de un equipo a través del servicio.
     *
     * @param id Identificador del equipo.
     * @return Información del equipo.
     */
    suspend fun getTeam(id: Long): Team? {
        return withContext(Dispatchers.IO) {
            try {
                val call = RetrofitInstance.yellowLockerTeamService.getTeam(id)
                val response = call.execute()

                if (response.isSuccessful) {
                    response.body()
                } else if (response.code() == 401) {
                    throw Exception("401")
                } else {
                    println("Fetch failed with code: ${response.code()} and message: ${response.message()}")
                    throw Exception("500")
                }
            } catch (e: Exception) {
                println("Fetch request failed: ${e.message}")
                throw Exception(e.message)
            }
        }
    }

    /**
     * Función que obtiene todos los equipos de la base de datos.
     *
     * @return Lista con la información de todos los equipos.
     */
    @Throws(Exception::class)
    suspend fun getAllTeams(): List<Team>? {
        return withContext(Dispatchers.IO) {
            try {
                val call = RetrofitInstance.yellowLockerTeamService.getAllTeams()
                val response = call.execute()

                if (response.isSuccessful) {
                    response.body()
                } else if (response.code() == 401) {
                    throw Exception("401")
                } else {
                    println("Fetch failed with code: ${response.code()} and message: ${response.message()}")
                    throw Exception("Internal server error")
                }
            } catch (e: Exception) {
                println("Fetch request failed: ${e.message}")
                throw Exception(e.message)
            }
        }
    }

    /**
     * Función que realiza la unión de un usuario a un equipo a trvés del servicio.
     *
     * @param teamId Identificador del equipo.
     * @param userId Identificador del usuario.
     * @return Información del usuario.
     */
    @Throws(Exception::class)
    suspend fun joinTeam(teamId: Long, userId: Long): MobileUser? {
        return withContext(Dispatchers.IO) {
            try {
                val call = RetrofitInstance.yellowLockerTeamService.joinTeam(teamId, userId)
                val response = call.execute()

                if (response.isSuccessful) {
                    response.body()
                } else if (response.code() == 401) {
                    throw Exception("401")
                } else {
                    println("${response.code()}: ${response.message()}")
                    throw Exception("Internal server error.")
                }
            } catch (e: Exception) {
                println("${e.message}")
                throw Exception(e.message)
            }
        }
    }

    /**
     * Función que obtiene los miembros de un equipo a través del servicio.
     *
     * @param teamId Identificador del equipo.
     * @return Lista de la información de los miembros del equipo.
     */
    suspend fun getMembers(teamId: Long): List<MemberListElement> {
        return withContext(Dispatchers.IO) {
            try {
                val call = RetrofitInstance.yellowLockerTeamService.getMembers(teamId)
                val response = call.execute()

                if (response.isSuccessful) {
                    response.body() ?: emptyList()
                } else if (response.code() == 401) {
                    throw Exception("401")
                } else {
                    throw Exception("Internal server error")
                }
            } catch (e: Exception) {
                throw Exception(e.message)
            }
        }
    }

    /**
     * Función que crea un equipo a través del servicio.
     *
     * @param teamCreation Información de creación del equipo.
     * @return Información del equipo creado.
     */
    suspend fun createTeam(teamCreation: TeamCreation): Team? {
        return withContext(Dispatchers.IO) {
            try {
                val call = RetrofitInstance.yellowLockerTeamService.createTeam(teamCreation)
                val response = call.execute()

                if (response.isSuccessful) {
                    response.body()
                } else if (response.code() == 401) {
                    throw Exception("401")
                } else {
                    throw Exception("Internal server error")
                }
            } catch (e: Exception) {
                throw Exception(e.message)
            }
        }
    }

    /**
     * Función que ejecuta la petición para abandonar un equipo a través del servicio.
     *
     * @param userId Identificador del usuario.
     * @param teamId Identificador del equipo.
     * @return Información del equipo que el usuario ha abandonado.
     */
    suspend fun leaveTeam(userId: Long, teamId: Long): Team? {
        return withContext(Dispatchers.IO) {
            try {
                val call = RetrofitInstance.yellowLockerTeamService.leaveTeam(userId, teamId)
                val response = call.execute()

                if (response.isSuccessful) {
                    response.body()
                } else if (response.code() == 401) {
                    throw Exception("401")
                } else {
                    throw Exception("Internal server error")
                }
            } catch (e: Exception) {
                throw Exception(e.message)
            }
        }
    }

}