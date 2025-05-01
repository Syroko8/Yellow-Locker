package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamMembers

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.MemberListElement
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamMembersScreenViewModel @Inject constructor(
    private val teamRepository: TeamRepository,
    @ApplicationContext private val context: Context
): ViewModel(){

    private val _members = MutableStateFlow<List<MemberListElement>>(emptyList())
    val members: StateFlow<List<MemberListElement>> get() = _members

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _logout = MutableStateFlow(false)
    val logout: StateFlow<Boolean> get() = _logout

     fun getMembers(teamId: Long){
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = ""
            try {
                val response = teamRepository.getMembers(teamId)
                _members.value = response
            } catch (e:Exception) {
                if(e.message == "401"){
                    _logout.value = true
                    _errorMessage.value = context.getString(R.string.no_valid_token)
                } else {
                    _errorMessage.value = e.message?: context.getString(R.string.err_exception)
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