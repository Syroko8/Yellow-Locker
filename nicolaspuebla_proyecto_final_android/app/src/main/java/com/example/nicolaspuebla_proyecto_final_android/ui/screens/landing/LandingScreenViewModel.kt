package com.example.nicolaspuebla_proyecto_final_android.ui.screens.landing

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.UserRepository
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    val teamList = mutableStateOf<List<Team>>(emptyList())

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _logout = MutableStateFlow<Boolean>(false)
    val logout: StateFlow<Boolean> get() = _logout


    fun getUser(){
        viewModelScope.launch{
            _loading.value = true
            _errorMessage.value = ""
            try {
                val response = userRepository.getUser(SessionManager.user?.id!!)
                SessionManager.user = response
                teamList.value = response?.teamList ?: emptyList()
            } catch (e: Exception){
                if(e.message == "401"){
                    _logout.value = true
                } else {
                    _errorMessage.value = e.message ?: "Error"
                }
            } finally {
                _loading.value = false
            }
        }
    }

    fun unsetError(){
        _errorMessage.value = ""
    }
}
