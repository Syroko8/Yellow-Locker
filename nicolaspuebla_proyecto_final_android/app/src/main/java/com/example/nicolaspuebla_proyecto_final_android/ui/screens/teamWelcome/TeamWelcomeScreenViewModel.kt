package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamWelcome

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Match
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamRolPK
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
    private val teamRolRepository: TeamRolRepository,
    @ApplicationContext private val context: Context
): ViewModel() {

    private val _team = MutableStateFlow<Team?>(null)
    val team: StateFlow<Team?> = _team.asStateFlow()

    val matches = mutableStateOf<Int>(0)

    val victories = mutableStateOf<Int>(0)

    val loses = mutableStateOf<Int>(0)

    val draws = mutableStateOf<Int>(0)

    private val _logout = MutableStateFlow<Boolean>(false)
    val logout: StateFlow<Boolean> get() = _logout

    private val _errMessage = MutableStateFlow<String>("")
    val errMessage: StateFlow<String> get() = _errMessage

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _showDialog = MutableStateFlow<Boolean>(false)
    val showDialog: StateFlow<Boolean> get() = _showDialog

    fun getTeam(id:Long){
        viewModelScope.launch {
            _loading.value = true
            _errMessage.value = ""
            try {
                val response = teamRepository.getTeam(id)
                _team.value = response
                calculateStatistics()
            } catch (e: Error){
                if(e.message == "401"){
                    _errMessage.value = context.getString(R.string.expired_session)
                    _showDialog.value = true
                } else if(e.message == "500") {
                    _errMessage.value = context.getString(R.string.failed_fetch)
                }
            } finally {
                _loading.value = false
            }
        }
    }

    private fun calculateStatistics(){
        if(_team.value?.eventList?.isNotEmpty() == true){
            _team.value?.eventList?.forEach {
                if(it is Match){
                    matches.value++
                    when {
                        (it.ownGoals ?: 0) > (it.oponentGoals ?: 0) -> victories.value++
                        (it.ownGoals ?: 0) < (it.oponentGoals ?: 0) -> loses.value--
                        else -> draws.value++
                    }
                }
            }
        }
    }

    fun getTeamRolLevel(){
        viewModelScope.launch {
            _loading.value = true
            _errMessage.value = ""
            try {
                val response = teamRolRepository.getRolLevel(TeamRolPK(_team.value?.id!!, SessionManager.user?.id!!))
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