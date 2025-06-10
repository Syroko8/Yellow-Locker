package com.example.nicolaspuebla_proyecto_final_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.AuthRepository
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model con el que la MainActivity puede realizar una petición de logout.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    /**
     * Función que realiza una petición de logout a través del repositorio.
     */
    fun logout() {
        viewModelScope.launch {
            try {
                repository.logout()
                SessionManager.setLogOut(false)
            } catch (e: Exception) {
                println("Logout error: ${e.message}")
            }
        }
    }
}
