package com.example.nicolaspuebla_proyecto_final_android.ui.screens.landing

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandingScreenViewModel @Inject constructor(

): ViewModel() {

    //val teamList = mutableStateOf<List<Team>>(emptyList())
    val teamList = mutableStateOf<List<Team>>(
        listOf(
            Team(
                id = 1L,
                name = "Real Madrid CF",
                locality = 32,
                logo = "https://ejemplo.com/logos/realmadrid.png",
                chatKey = "CHAT_RM_123",
                sport = "Fútbol",
                positions = listOf(1L, 2L, 3L, 4L),
                messages = listOf(101L, 102L, 103L),
                teamRoles = listOf(1L, 2L, 3L),
                eventList = listOf(201L, 202L),
                members = listOf(1001L, 1002L, 1003L)
            ),
            Team(
                id = 2L,
                name = "Golden State Warriors",
                locality = 65,
                logo = "https://ejemplo.com/logos/warriors.png",
                chatKey = "CHAT_GSW_456",
                sport = "Baloncesto",
                positions = listOf(5L, 6L, 7L),
                messages = listOf(104L, 105L),
                teamRoles = listOf(4L, 5L),
                eventList = listOf(203L, 204L, 205L),
                members = listOf(1004L, 1005L)
            ),
            Team(
                id = 3L,
                name = "Scuderia Ferrari",
                locality = 45,
                logo = "https://ejemplo.com/logos/ferrari.png",
                chatKey = "CHAT_FERRARI_789",
                sport = "Fórmula 1",
                positions = listOf(8L, 9L),
                messages = listOf(106L),
                teamRoles = listOf(6L, 7L, 8L),
                eventList = listOf(206L),
                members = listOf(1006L, 1007L, 1008L)
            ),
            Team(
                id = 4L,
                name = "New York Yankees",
                locality = 32,
                logo = "https://ejemplo.com/logos/yankees.png",
                chatKey = "CHAT_NYY_321",
                sport = "Béisbol",
                positions = listOf(10L, 11L),
                messages = listOf(107L, 108L, 109L),
                teamRoles = listOf(9L, 10L),
                eventList = listOf(207L, 208L),
                members = listOf(1009L, 1010L)
            ),
            Team(
                id = 5L,
                name = "Tokyo Volleys",
                locality = 56,
                logo = "https://ejemplo.com/logos/tokyovolleys.png",
                chatKey = "CHAT_TKV_654",
                sport = "Vóleibol",
                positions = listOf(12L, 13L),
                messages = listOf(110L),
                teamRoles = listOf(11L, 12L),
                eventList = listOf(209L),
                members = listOf(1011L, 1012L, 1013L)
            ),
            Team(
                id = 1L,
                name = "Real Madrid CF",
                locality = 32,
                logo = "https://ejemplo.com/logos/realmadrid.png",
                chatKey = "CHAT_RM_123",
                sport = "Fútbol",
                positions = listOf(1L, 2L, 3L, 4L),
                messages = listOf(101L, 102L, 103L),
                teamRoles = listOf(1L, 2L, 3L),
                eventList = listOf(201L, 202L),
                members = listOf(1001L, 1002L, 1003L)
            ),
            Team(
                id = 2L,
                name = "Golden State Warriors",
                locality = 65,
                logo = "https://ejemplo.com/logos/warriors.png",
                chatKey = "CHAT_GSW_456",
                sport = "Baloncesto",
                positions = listOf(5L, 6L, 7L),
                messages = listOf(104L, 105L),
                teamRoles = listOf(4L, 5L),
                eventList = listOf(203L, 204L, 205L),
                members = listOf(1004L, 1005L)
            ),
            Team(
                id = 3L,
                name = "Scuderia Ferrari",
                locality = 45,
                logo = "https://ejemplo.com/logos/ferrari.png",
                chatKey = "CHAT_FERRARI_789",
                sport = "Fórmula 1",
                positions = listOf(8L, 9L),
                messages = listOf(106L),
                teamRoles = listOf(6L, 7L, 8L),
                eventList = listOf(206L),
                members = listOf(1006L, 1007L, 1008L)
            ),
            Team(
                id = 4L,
                name = "New York Yankees",
                locality = 32,
                logo = "https://ejemplo.com/logos/yankees.png",
                chatKey = "CHAT_NYY_321",
                sport = "Béisbol",
                positions = listOf(10L, 11L),
                messages = listOf(107L, 108L, 109L),
                teamRoles = listOf(9L, 10L),
                eventList = listOf(207L, 208L),
                members = listOf(1009L, 1010L)
            ),
            Team(
                id = 5L,
                name = "Tokyo Volleys",
                locality = 56,
                logo = "https://ejemplo.com/logos/tokyovolleys.png",
                chatKey = "CHAT_TKV_654",
                sport = "Vóleibol",
                positions = listOf(12L, 13L),
                messages = listOf(110L),
                teamRoles = listOf(11L, 12L),
                eventList = listOf(209L),
                members = listOf(1011L, 1012L, 1013L)
            )
        )
    )

    // TODO
    fun getTeamLocality(locality_id: Long): String{

        return ""
    }

    // TODO función para recorrer los equipos del session manager y sacar su objeto por api y meterlo en la lista.
}