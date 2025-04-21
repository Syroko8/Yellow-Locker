package com.example.nicolaspuebla_proyecto_final_android.ui.screens.landing

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.TeamInfo
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Locality
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandingScreenViewModel @Inject constructor(

): ViewModel() {
    val teamList = mutableStateOf<List<Team>>(SessionManager.user?.teamList ?: emptyList())
}
