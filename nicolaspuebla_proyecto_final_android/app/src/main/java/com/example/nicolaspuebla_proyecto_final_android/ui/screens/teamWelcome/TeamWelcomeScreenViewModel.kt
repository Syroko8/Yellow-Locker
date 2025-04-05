package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamWelcome

import androidx.lifecycle.ViewModel
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TeamWelcomeScreenViewModel @Inject constructor(

): ViewModel() {
    private val _team = MutableStateFlow<Team?>(
        Team(
        id = 4L,
        name = "New York ",
        locality = 32,
        logo = "https://ejemplo.com/logos/yankees.png",
        chatKey = "CHAT_NYY_321",
        sport = "Béisbol",
        positions = listOf(10L, 11L),
        messages = listOf(107L, 108L, 109L),
        teamRoles = listOf(9L, 10L),
        eventList = listOf(207L, 208L),
        members = listOf(1009L, 1010L)
    )
    )
    val team: StateFlow<Team?> = _team.asStateFlow()

    private val _locality = MutableStateFlow<String>("")
    val locality: StateFlow<String> = _locality.asStateFlow()

    // TODO
    fun getTeamLocality(){

    }
}