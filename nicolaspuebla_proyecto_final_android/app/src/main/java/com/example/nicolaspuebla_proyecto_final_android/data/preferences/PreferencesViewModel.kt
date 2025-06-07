package com.example.nicolaspuebla_proyecto_final_android.data.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Clase que permite que expone los métodos de PreferencesRepository a las pantallas de la aplicación.
 */
@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel() {

    val token: StateFlow<String> =
        preferencesRepository.tokenFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )

    val userId: StateFlow<Long?> =
        preferencesRepository.userIdFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun saveToken(newToken: String) {
        viewModelScope.launch {
            preferencesRepository.saveToken(newToken = newToken)
        }
    }

    fun saveUserId(userId: Long){
        viewModelScope.launch {
            preferencesRepository.saveUserId(userId)
        }
    }

    suspend fun clearUserData() {
        preferencesRepository.clearUserData()
    }
}