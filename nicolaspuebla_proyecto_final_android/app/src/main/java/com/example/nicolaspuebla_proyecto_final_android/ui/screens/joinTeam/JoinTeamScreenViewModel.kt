package com.example.nicolaspuebla_proyecto_final_android.ui.screens.joinTeam

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.TeamInfo
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamRepository
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinTeamScreenViewModel @Inject constructor(
    private val teamRepository: TeamRepository,
    @ApplicationContext private val context: Context
): ViewModel() {

    private val teamList = MutableStateFlow<List<TeamInfo>>(emptyList())

    private val _filteredTeamList = MutableStateFlow<List<TeamInfo>>(emptyList())
    val filteredTeamList: StateFlow<List<TeamInfo>> get() = _filteredTeamList

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _logout = MutableStateFlow<Boolean>(false)
    val logout: StateFlow<Boolean> get() = _logout

    private val _choseTeam = MutableStateFlow<Boolean>(false)
    val choseTeam: StateFlow<Boolean> get() = _choseTeam

    private val _chosenTeam = MutableStateFlow<TeamInfo?>(null)
    val chosenTeam: StateFlow<TeamInfo?> get() = _chosenTeam

    private val _successfullyJoined = MutableStateFlow<Boolean>(false)
    val successfullyJoined: StateFlow<Boolean> get() = _successfullyJoined

    var teamName = mutableStateOf("")

    fun unsetError() {
        _errorMessage.value = ""
    }

    fun getTeams(){
        viewModelScope.launch {
            _loading.value = true
            _errorMessage.value = ""

            try {
                val response = teamRepository.getAllTeams()
                teamList.value = response ?: emptyList()
            } catch (e: Exception){
                if(e.message == "401"){
                    _logout.value = true
                } else {
                    _errorMessage.value =  e.message ?: context.getString(R.string.err_exception)
                }
            } finally {
                _loading.value = false
            }
        }
    }

    fun filterTeams() {
        viewModelScope.launch {
            val newFilteredList = teamList.value.filter {
                it.name.contains(teamName.value, ignoreCase = true)
            }
            _filteredTeamList.value = newFilteredList
        }
    }

    fun joinTeam(teamId: Long) {
        viewModelScope.launch {
            _loading.value = true
            _errorMessage.value = ""
            try {
                val response = teamRepository.joinTeam(teamId, SessionManager.user?.id!!)
                if(response != null){
                    _successfullyJoined.value = true
                    SessionManager.user = response
                    println(SessionManager.user)
                }
            } catch (e: Exception){
                if(e.message == "401"){
                    _logout.value = true
                } else{
                    _errorMessage.value = e.message!!
                }
            } finally {
                _loading.value = false
            }
        }
    }

    fun setChoseTeam(value: Boolean) {
        _choseTeam.value = value
    }

    fun setChosenTeam(team: TeamInfo) {
        _chosenTeam.value = team
    }

    fun unsetChosenTeam() {
        _chosenTeam.value = null
    }

    fun unsetSuccessfullyJoined(){
        _successfullyJoined.value = false
    }

    fun checkAlreadyBelong(): Boolean {
        val userTeamList = SessionManager.user?.teamList

        userTeamList?.forEach {
            if (it.id == _chosenTeam.value?.id){
                return true
            }
        }
        return false
    }
}