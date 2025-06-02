package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.MemberListElement
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.TeamCreation
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.TeamLeaveRequest
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamRepository {

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

    suspend fun leaveTeam(request: TeamLeaveRequest): Team? {
        return withContext(Dispatchers.IO) {
            try {
                val call = RetrofitInstance.yellowLockerTeamService.leaveTeam(request)
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