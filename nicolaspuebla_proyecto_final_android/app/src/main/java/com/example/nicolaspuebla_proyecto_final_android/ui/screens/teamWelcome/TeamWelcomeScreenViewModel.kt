package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamWelcome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.TeamResponse
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Locality
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamPosition
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamRol
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.User
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.sql.Date
import javax.inject.Inject

@HiltViewModel
class TeamWelcomeScreenViewModel @Inject constructor(
    private val teamRepository: TeamRepository,
    @ApplicationContext private val context: Context
): ViewModel() {
    private val _team = MutableStateFlow<TeamResponse?>(
        TeamResponse(
                id = 1L,
            name = "Real Madrid CF",
            locality = Locality(32L, "Madrid"),
            logo = "https://ejemplo.com/logos/realmadrid.png",
            chatKey = "CHAT_RM_123",
            sport = "Fútbol",
            positions = listOf(
                TeamPosition(1L, 1L, "Delantero"),
                TeamPosition(2L, 1L, "Centrocampista"),
                TeamPosition(3L, 1L, "Defensa"),
                TeamPosition(4L, 1L, "Portero")
            ),
            messages = listOf(101L, 102L, 103L),
            teamRoles = listOf(
                TeamRol(1001L, 1L),
                TeamRol(1002L, 1L),
                TeamRol(1003L, 1L)
            ),
            eventList = listOf(
                Event(201L, 1L, 28001L, 40, -3, Date(2023, 10, 15)),
                Event(202L, 1L, 28002L, 41, -4, Date(2023, 11, 20))
            ),
            members = listOf(
                User(1001L, "Juan", "Pérez", "juan@realmadrid.com", "pass123"),
                User(1002L, "Carlos", "Gómez", "carlos@realmadrid.com", "pass456"),
                User(1003L, "Luis", "Martínez", "luis@realmadrid.com", "pass789")
            )
        )
    )
    val team: StateFlow<TeamResponse?> = _team.asStateFlow()

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
                val response = teamRepository.getTeam(id = id)

                if(response != "401" && response != null){
                    _team.value = response as TeamResponse?
                }else if(response == "401"){
                        _errMessage.value = context.getString(R.string.expired_session)
                        _showDialog.value = true
                } else {
                    _errMessage.value = context.getString(R.string.failed_fetch)
                }
            } catch (e: Error){
                _errMessage.value = e.message.toString()
            } finally {
                _loading.value = false
            }
        }
    }
}