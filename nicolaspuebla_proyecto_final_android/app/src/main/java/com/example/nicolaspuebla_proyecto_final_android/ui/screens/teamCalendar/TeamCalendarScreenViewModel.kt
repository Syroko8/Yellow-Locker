package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamCalendar

import androidx.lifecycle.ViewModel
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Locality
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Match
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Message
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamRol
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamRolPK
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TeamCalendarScreenViewModel @Inject constructor(

): ViewModel(){

    private val _sheetVisibility = MutableStateFlow(false)
    val sheetVisibility: StateFlow<Boolean> get() = _sheetVisibility

    private val _events = MutableStateFlow<List<Event>>(

        listOf(
            Match(
                id = 1,
                team = Team(
                    id = 1,
                    name = "Team1",
                    locality = Locality(1, "dede"),
                    logo = "logo",
                    chatKey = "chatkey",
                    sport = "Football",
                    positions = emptyList(),
                    messages = listOf(Message(1)),
                    teamRoles = emptyList(),
                    members = emptyList(),
                    assignedPositions = emptyList()
                ),
                address = "adress",
                latitude = 0.0,
                longitude = 0.0,
                date = "2025-04-15T13:00:00.000+00:00",
                oponent = Team(
                    id = 2,
                    name = "Team2",
                    locality = Locality(1, "dede"),
                    logo = "logo",
                    chatKey = "chatkey",
                    sport = "Volleyball",
                    positions = emptyList(),
                    messages = emptyList(),
                    teamRoles = listOf(TeamRol(TeamRolPK(252, 2))),
                    members = listOf(
                        MobileUser(
                            id = 252,
                            name = "Nico",
                            surname = "Puebla ",
                            email = "nico@gmail.com",
                            password = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3",
                            disabled = false,
                            birthDate = "2005-10-28",
                            age = 19,
                            messages = emptyList(),
                            teamRoles = listOf(TeamRol(TeamRolPK(252, 2)))
                        )
                    ),
                    assignedPositions = emptyList()
                ),
                own_goals = 0,
                oponent_goals = 3
            ), Match(
                id = 2,
                team = Team(
                    id = 1,
                    name = "Team1",
                    locality = Locality(1, "dede"),
                    logo = "logo",
                    chatKey = "chatkey",
                    sport = "Football",
                    positions = emptyList(),
                    messages = listOf(Message(1)),
                    teamRoles = emptyList(),
                    members = emptyList(),
                    assignedPositions = emptyList()
                ),
                address = "adress",
                latitude = 0.0,
                longitude = 0.0,
                date = "2025-04-15T13:00:00.000+00:00",
                oponent = Team(
                    id = 2,
                    name = "Team2",
                    locality = Locality(1, "dede"),
                    logo = "logo",
                    chatKey = "chatkey",
                    sport = "Volleyball",
                    positions = emptyList(),
                    messages = emptyList(),
                    teamRoles = listOf(TeamRol(TeamRolPK(252, 2))),
                    members = listOf(
                        MobileUser(
                            id = 252,
                            name = "Nico",
                            surname = "Puebla ",
                            email = "nico@gmail.com",
                            password = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3",
                            disabled = false,
                            birthDate = "2005-10-28",
                            age = 19,
                            messages = emptyList(),
                            teamRoles = listOf(TeamRol(TeamRolPK(252, 2)))
                        )
                    ),
                    assignedPositions = emptyList()
                ),
                own_goals = 0,
                oponent_goals = 3
            ),Match(
                id = 3,
                team = Team(
                    id = 1,
                    name = "Team1",
                    locality = Locality(1, "dede"),
                    logo = "logo",
                    chatKey = "chatkey",
                    sport = "Football",
                    positions = emptyList(),
                    messages = listOf(Message(1)),
                    teamRoles = emptyList(),
                    members = emptyList(),
                    assignedPositions = emptyList()
                ),
                address = "adress",
                latitude = 0.0,
                longitude = 0.0,
                date = "2025-04-15T13:00:00.000+00:00",
                oponent = Team(
                    id = 2,
                    name = "Team2",
                    locality = Locality(1, "dede"),
                    logo = "logo",
                    chatKey = "chatkey",
                    sport = "Volleyball",
                    positions = emptyList(),
                    messages = emptyList(),
                    teamRoles = listOf(TeamRol(TeamRolPK(252, 2))),
                    members = listOf(
                        MobileUser(
                            id = 252,
                            name = "Nico",
                            surname = "Puebla ",
                            email = "nico@gmail.com",
                            password = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3",
                            disabled = false,
                            birthDate = "2005-10-28",
                            age = 19,
                            messages = emptyList(),
                            teamRoles = listOf(TeamRol(TeamRolPK(252, 2)))
                        )
                    ),
                    assignedPositions = emptyList()
                ),
                own_goals = 0,
                oponent_goals = 3
            )
        )
    )
    val events: StateFlow<List<Event>> get() = _events

    private val _selectedEvents = MutableStateFlow<List<Event>>(emptyList())
    val selectedEvents: StateFlow<List<Event>> get() = _selectedEvents

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _logout = MutableStateFlow(false)
    val logout: StateFlow<Boolean> get() = _logout

    fun setSelectedEvents(events: List<Event>){
        _selectedEvents.value = events
    }

    fun showSheet() {
        _sheetVisibility.value = true
    }

    fun hideSheet() {
        _sheetVisibility.value = false
    }
}