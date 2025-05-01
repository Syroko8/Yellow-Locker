package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.MatchReciever
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Match
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamEventRepository {

    fun matchRecieverToMatch(match: MatchReciever): Match{
        return Match(
            match.id,
            match.team,
            match.address,
            match.latitude,
            match.longitude,
            match.date,
            match.oponent,
            match.own_goals,
            match.own_goals
        )
    }

    suspend fun getTeamMatches(teamId: Long): List<Match>{
        return withContext(Dispatchers.IO){
            try {
                val call = RetrofitInstance.yellowLockerTeamEventService.getTeamMatches(teamId)
                val response = call.execute()

                if(response.isSuccessful){
                    response.body()?.map {
                        matchRecieverToMatch(it)
                    } ?: emptyList()
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