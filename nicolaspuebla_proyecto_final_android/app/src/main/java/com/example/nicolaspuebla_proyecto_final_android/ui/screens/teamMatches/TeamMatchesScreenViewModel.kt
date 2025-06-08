package com.example.nicolaspuebla_proyecto_final_android.ui.screens.TeamMatches

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Match
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel para la pantalla de partidos del equipo.
 *
 * @property teamEventRepository Repositorio para acceso a datos de eventos.
 * @property context Contexto de aplicación para recursos.
 */
@HiltViewModel
class TeamMatchesScreenViewModel @Inject constructor(
    private val teamEventRepository: TeamEventRepository,
    @ApplicationContext private val context: Context
): ViewModel(){

    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    val matches: StateFlow<List<Match>> get() = _matches

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _logout = MutableStateFlow(false)
    val logout: StateFlow<Boolean> get() = _logout

    /**
     * Obtiene los partidos del equipo desde el repositorio.
     * Maneja estados de carga y posibles errores.
     *
     * @param teamId Identificador del equipo actual.
     */
    fun getMatches(teamId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = ""
            try {
                val response = teamEventRepository.getTeamMatches(teamId)
                _matches.value = response
            } catch (e:Exception) {
                if(e.message == "401"){
                    _logout.value = true
                    _errorMessage.value = e.message ?: context.getString(R.string.err_exception)
                } else {
                    _errorMessage.value = e.message ?: context.getString(R.string.err_exception)
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun unsetError() {
        _errorMessage.value = ""
    }
}