package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamWelcome

import androidx.lifecycle.ViewModel
import com.example.nicolaspuebla_proyecto_final_android.data.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TeamWelcomeScreenViewModel @Inject constructor(

): ViewModel() {
    private val _team = MutableStateFlow<Team?>(null)
    val team: StateFlow<Team?> = _team.asStateFlow()

}