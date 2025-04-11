package com.example.nicolaspuebla_proyecto_final_android.ui.screens.landing

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.LandingScreenTeams
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Locality
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandingScreenViewModel @Inject constructor(

): ViewModel() {

    //val teamList = mutableStateOf<List<Team>>(emptyList())
    val teamList = mutableStateOf<List<LandingScreenTeams>>(
        listOf(
            LandingScreenTeams(
                id = 1L,
                name = "Real Madrid CF",
                locality = Locality(32L, "Madrid", listOf(1L)),
                logo = "https://ejemplo.com/logos/realmadrid.png",
                sport = "Fútbol"
            ),
            LandingScreenTeams(
                id = 2L,
                name = "Golden State Warriors",
                locality = Locality(65L, "California", listOf(2L)),
                logo = "https://ejemplo.com/logos/warriors.png",
                sport = "Baloncesto"
            ),
            LandingScreenTeams(
                id = 3L,
                name = "Scuderia Ferrari",
                locality = Locality(45L, "Maranello", listOf(3L)),
                logo = "https://ejemplo.com/logos/ferrari.png",
                sport = "Fórmula 1"
            ),
            LandingScreenTeams(
                id = 4L,
                name = "New York Yankees",
                locality = Locality(32L, "Nueva York", listOf(4L)),
                logo = "https://ejemplo.com/logos/yankees.png",
                sport = "Béisbol"
            ),
            LandingScreenTeams(
                id = 5L,
                name = "Tokyo Volleys",
                locality = Locality(56L, "Tokio", listOf(5L)),
                logo = "https://ejemplo.com/logos/tokyovolleys.png",
                sport = "Vóleibol"
            ),
            LandingScreenTeams(
                id = 6L,
                name = "Real Madrid CF",
                locality = Locality(32L, "Madrid", listOf(6L)),
                logo = "https://ejemplo.com/logos/realmadrid.png",
                sport = "Fútbol"
            ),
            LandingScreenTeams(
                id = 7L,
                name = "Golden State Warriors",
                locality = Locality(65L, "California", listOf(7L)),
                logo = "https://ejemplo.com/logos/warriors.png",
                sport = "Baloncesto"
            ),
            LandingScreenTeams(
                id = 8L,
                name = "Scuderia Ferrari",
                locality = Locality(45L, "Maranello", listOf(8L)),
                logo = "https://ejemplo.com/logos/ferrari.png",
                sport = "Fórmula 1"
            ),
            LandingScreenTeams(
                id = 9L,
                name = "New York Yankees",
                locality = Locality(32L, "Nueva York", listOf(9L)),
                logo = "https://ejemplo.com/logos/yankees.png",
                sport = "Béisbol"
            ),
            LandingScreenTeams(
                id = 10L,
                name = "Tokyo Volleys",
                locality = Locality(56L, "Tokio", listOf(10L)),
                logo = "https://ejemplo.com/logos/tokyovolleys.png",
                sport = "Vóleibol"
            )
        )
    )
}

/*

TeamResponse(
                id = 1L,
                name = "Real Madrid CF",
                locality = Locality(32L, "Madrid", listOf(1L)),
                logo = "https://ejemplo.com/logos/realmadrid.png",
                chatKey = "CHAT_RM_123",
                sport = "Fútbol",
                positions = listOf(
                    TeamPositions(1L, 1L, "Delantero"),
                    TeamPositions(2L, 1L, "Centrocampista"),
                    TeamPositions(3L, 1L, "Defensa"),
                    TeamPositions(4L, 1L, "Portero")
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
            ),

            // Equipo 2 - Golden State Warriors
            TeamResponse(
                id = 2L,
                name = "Golden State Warriors",
                locality = Locality(65L, "California", listOf(2L)),
                logo = "https://ejemplo.com/logos/warriors.png",
                chatKey = "CHAT_GSW_456",
                sport = "Baloncesto",
                positions = listOf(
                    TeamPositions(5L, 2L, "Base"),
                    TeamPositions(6L, 2L, "Alero"),
                    TeamPositions(7L, 2L, "Pívot")
                ),
                messages = listOf(104L, 105L),
                teamRoles = listOf(
                    TeamRol(1004L, 2L),
                    TeamRol(1005L, 2L)
                ),
                eventList = listOf(
                    Event(203L, 2L, 94001L, 37, -122, Date(2023, 12, 5)),
                    Event(204L, 2L, 94002L, 38, -123, Date(2024, 1, 10)),
                    Event(205L, 2L, 94003L, 39, -124, Date(2024, 2, 15))
                ),
                members = listOf(
                    User(1004L, "Stephen", "Curry", "steph@warriors.com", "pass321"),
                    User(1005L, "Klay", "Thompson", "klay@warriors.com", "pass654")
                )
            ),

            // Equipo 3 - Scuderia Ferrari (Versión 1)
            TeamResponse(
                id = 3L,
                name = "Scuderia Ferrari",
                locality = Locality(45L, "Maranello", listOf(3L)),
                logo = "https://ejemplo.com/logos/ferrari.png",
                chatKey = "CHAT_FERRARI_789",
                sport = "Fórmula 1",
                positions = listOf(
                    TeamPositions(8L, 3L, "Piloto"),
                    TeamPositions(9L, 3L, "Ingeniero")
                ),
                messages = listOf(106L),
                teamRoles = listOf(
                    TeamRol(1006L, 3L),
                    TeamRol(1007L, 3L),
                    TeamRol(1008L, 3L)
                ),
                eventList = listOf(
                    Event(206L, 3L, 41053L, 44, 11, Date(2023, 9, 1))
                ),
                members = listOf(
                    User(1006L, "Charles", "Leclerc", "charles@ferrari.com", "pass987"),
                    User(1007L, "Carlos", "Sainz", "carlos@ferrari.com", "pass654"),
                    User(1008L, "Frédéric", "Vasseur", "fred@ferrari.com", "pass321")
                )
            ),

            // Equipo 4 - New York Yankees (Versión 1)
            TeamResponse(
                id = 4L,
                name = "New York Yankees",
                locality = Locality(32L, "Nueva York", listOf(4L)),
                logo = "https://ejemplo.com/logos/yankees.png",
                chatKey = "CHAT_NYY_321",
                sport = "Béisbol",
                positions = listOf(
                    TeamPositions(10L, 4L, "Lanzador"),
                    TeamPositions(11L, 4L, "Receptor")
                ),
                messages = listOf(107L, 108L, 109L),
                teamRoles = listOf(
                    TeamRol(1009L, 4L),
                    TeamRol(1010L, 4L)
                ),
                eventList = listOf(
                    Event(207L, 4L, 10001L, 40, -74, Date(2023, 7, 4)),
                    Event(208L, 4L, 10002L, 41, -75, Date(2023, 8, 15))
                ),
                members = listOf(
                    User(1009L, "Aaron", "Judge", "aaron@yankees.com", "pass135"),
                    User(1010L, "Giancarlo", "Stanton", "giancarlo@yankees.com", "pass246")
                )
            ),

            // Equipo 5 - Tokyo Volleys (Versión 1)
            TeamResponse(
                id = 5L,
                name = "Tokyo Volleys",
                locality = Locality(56L, "Tokio", listOf(5L)),
                logo = "https://ejemplo.com/logos/tokyovolleys.png",
                chatKey = "CHAT_TKV_654",
                sport = "Vóleibol",
                positions = listOf(
                    TeamPositions(12L, 5L, "Armador"),
                    TeamPositions(13L, 5L, "Central")
                ),
                messages = listOf(110L),
                teamRoles = listOf(
                    TeamRol(1011L, 5L),
                    TeamRol(1012L, 5L)
                ),
                eventList = listOf(
                    Event(209L, 5L, 10001L, 35, 139, Date(2023, 6, 25))
                ),
                members = listOf(
                    User(1011L, "Yuki", "Ishikawa", "yuki@volleys.jp", "pass369"),
                    User(1012L, "Ran", "Takahashi", "ran@volleys.jp", "pass159"),
                    User(1013L, "Akihiro", "Fukatsu", "akihiro@volleys.jp", "pass357")
                )
            )

* */