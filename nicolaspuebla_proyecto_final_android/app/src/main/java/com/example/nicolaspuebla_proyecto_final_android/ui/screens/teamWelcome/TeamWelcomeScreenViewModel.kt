package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamWelcome

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Match
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamRolPK
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamEventRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamRolRepository
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamWelcomeScreenViewModel @Inject constructor(
    private val teamRepository: TeamRepository,
    private val eventRepository: TeamEventRepository,
    private val teamRolRepository: TeamRolRepository,
    @ApplicationContext private val context: Context
): ViewModel() {

    private val _team = MutableStateFlow<Team?>(null)
    val team: StateFlow<Team?> = _team.asStateFlow()

    val matches = mutableStateOf<Int>(0)

    val victories = mutableStateOf<Int>(0)

    val loses = mutableStateOf<Int>(0)

    val draws = mutableStateOf<Int>(0)

    var leftTeam = mutableStateOf(false)

    private val _logout = MutableStateFlow<Boolean>(false)
    val logout: StateFlow<Boolean> get() = _logout

    private val _errMessage = MutableStateFlow<String>("")
    val errMessage: StateFlow<String> get() = _errMessage

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading

    fun getTeam(id:Long){
        viewModelScope.launch {
            _loading.value = true
            _errMessage.value = ""
            try {
                val response = teamRepository.getTeam(id)
                val teamListResponse = eventRepository.getTeamMatches(id)
                _team.value = response
                calculateStatistics(teamListResponse)
                response?.id?.let { SessionManager.setTeamId(it) }
                getTeamRolLevel()
            } catch (e: Exception){
                if(e.message == "401"){
                    _errMessage.value = context.getString(R.string.expired_session)
                } else {
                    _errMessage.value = context.getString(R.string.internal_server_err)
                }
            } finally {
                _loading.value = false
            }
        }
    }

    fun leaveTeam(userId: Long, actualTeamId: Long) {
        viewModelScope.launch {
            _errMessage.value = ""
            _loading.value = true

            try {
                teamRepository.leaveTeam(userId, actualTeamId)
                SessionManager.leaveActualTeam.value = false
                leftTeam.value = true
            } catch (e: Exception){
                if(e.message == "401"){
                    _errMessage.value = context.getString(R.string.expired_session)
                } else {
                    _errMessage.value = context.getString(R.string.internal_server_err)
                }
            } finally {
                _loading.value = false
            }
        }
    }

    private fun calculateStatistics(list: List<Match>){
        matches.value = 0
        victories.value = 0
        loses.value = 0
        draws.value = 0

        if(list.isNotEmpty()){
            list.forEach {
                matches.value++
                when {
                    (it.ownGoals ?: 0) > (it.opponentGoals ?: 0) -> victories.value++
                    (it.ownGoals ?: 0) < (it.opponentGoals ?: 0) -> loses.value++
                    else -> draws.value++
                }
            }
        }
    }

    private fun getTeamRolLevel(){
        viewModelScope.launch {
            _loading.value = true
            _errMessage.value = ""
            try {
                val response = teamRolRepository.getRolLevel(TeamRolPK(SessionManager.user?.id!!, _team.value?.id!!))
                SessionManager.setTeamRole(response)
            } catch (e: Exception){
                if(e.message == "401"){
                    _logout.value = true
                } else {
                    _errMessage.value = e.message ?: "Error"
                }
            } finally {
                _loading.value = false
            }
        }
    }
}