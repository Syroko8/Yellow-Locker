package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.MatchReciever
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.TrainingReciever
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Match
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Training
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamEventRepository {

    fun matchRecieverToMatch(match: MatchReciever): Match{
        return Match(
            match.event_type,
            match.id,
            match.team,
            match.latitude,
            match.longitude,
            match.date,
            match.opponent,
            opponentGoals = match.opponentGoals,
            ownGoals = match.ownGoals
        )
    }

    fun trainingRecieverToTraining(training: TrainingReciever): Training{
        return Training(
            training.event_type,
            training.id,
            training.team,
            training.latitude,
            training.longitude,
            training.date,
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

    suspend fun getTeamTrainings(teamId: Long): List<Training>{
        return withContext((Dispatchers.IO)){
            try {
                val call = RetrofitInstance.yellowLockerTeamEventService.getTeamTrainings(teamId)
                val response = call.execute()

                if(response.isSuccessful){
                    response.body()?.map {
                        trainingRecieverToTraining(it)
                    } ?: emptyList()
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

    suspend fun updateEvent(event: Event): Event{
        return withContext((Dispatchers.IO)){
            try {
                println(">>>>>>>>>>>>${event}")
                val call = if(event is Match) RetrofitInstance.yellowLockerTeamEventService.updateEvent(
                    event,
                    event.opponent.id,
                    event.ownGoals,
                    event.opponentGoals
                ) else
                    RetrofitInstance.yellowLockerTeamEventService.updateEvent(event, null, null, null)
                val response = call.execute()
                if(response.isSuccessful){
                    response.body()!!
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

    suspend fun deleteEvent(eventId: Long): Long?{
        return withContext((Dispatchers.IO)){
            try {
                val call = RetrofitInstance.yellowLockerTeamEventService.deleteEvent(eventId)
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