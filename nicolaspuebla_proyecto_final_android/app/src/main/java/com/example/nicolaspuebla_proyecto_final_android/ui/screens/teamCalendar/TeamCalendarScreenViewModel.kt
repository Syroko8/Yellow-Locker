package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamCalendar

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamCalendarScreenViewModel @Inject constructor(
    private val teamEventRepository: TeamEventRepository,
    @ApplicationContext private val context: Context
): ViewModel(){

    private val _sheetVisibility = MutableStateFlow(false)
    val sheetVisibility: StateFlow<Boolean> get() = _sheetVisibility

    private val _events = MutableStateFlow<List<Event>>(emptyList())
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

    fun unsetErr() {
        _errorMessage.value = ""
    }

    fun getEvents(teamId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = ""
            try {
                val trainings = teamEventRepository.getTeamTrainings(teamId)
                val matches = teamEventRepository.getTeamMatches(teamId)

                val eventList = _events.value.toMutableList()
                eventList.addAll(matches)
                eventList.addAll(trainings)
                if (_events.value != eventList) {
                    _events.value = eventList
                }
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
}