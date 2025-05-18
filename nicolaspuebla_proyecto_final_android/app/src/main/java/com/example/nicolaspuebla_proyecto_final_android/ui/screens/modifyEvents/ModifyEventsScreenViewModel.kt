package com.example.nicolaspuebla_proyecto_final_android.ui.screens.modifyEvents

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Match
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Training
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamEventRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamRepository
import com.example.nicolaspuebla_proyecto_final_android.utils.LocationChoosingInfo
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
import javax.inject.Inject

@HiltViewModel
class ModifyEventsScreenViewModel @Inject constructor(
    private val teamEventRepository: TeamEventRepository,
    @ApplicationContext private val context: Context,
    private val teamRepository: TeamRepository
): ViewModel(){

    private val teamList = MutableStateFlow<List<Team>>(emptyList())

    private val _filteredTeamList = MutableStateFlow<List<Team>>(emptyList())
    val filteredTeamList: StateFlow<List<Team>> get() = _filteredTeamList

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> get() = _events

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _logout = MutableStateFlow(false)
    val logout: StateFlow<Boolean> get() = _logout

    private val _changeOpponentVisibility = MutableStateFlow<Boolean>(false)
    val changeOpponentVisibility: StateFlow<Boolean> get() = _changeOpponentVisibility

    private val _chosenEventToEdit = MutableStateFlow<Event?>(null)
    val chosenEventToEdit: StateFlow<Event?> get() = _chosenEventToEdit

    private val _datePickerVisibility = MutableStateFlow<Boolean>(false)
    val datePickerVisibility: StateFlow<Boolean> get() = _datePickerVisibility

    private val _timePickerVisibility = MutableStateFlow<Boolean>(false)
    val timePickerVisibility: StateFlow<Boolean> get() = _timePickerVisibility


    private val _mapVisibility = MutableStateFlow<Boolean>(false)
    val mapVisibility: StateFlow<Boolean> get() = _mapVisibility

    fun setMapVisibility(value: Boolean){
        _mapVisibility.value = value
    }

    var teamName = mutableStateOf("")

    fun setChosenEventToEdit(event: Event){
        _chosenEventToEdit.value = event
    }

    fun setDatePickerVisibility(value: Boolean) {
        _datePickerVisibility.value = value
    }

    fun setSelectedDate(time: OffsetDateTime){
        if(_chosenEventToEdit.value != null){
            val modified: Event = when(_chosenEventToEdit.value){
                is Match -> (_chosenEventToEdit.value as Match).copy(date = time.toString())
                is Training -> (_chosenEventToEdit.value as Training).copy(date = time.toString())
                else -> { throw Exception(context.getString(R.string.invalid_event_type)) }
            }

            val list = _events.value.toMutableList()
            list[list.indexOf(_chosenEventToEdit.value)] = modified
            saveModified(modified, list)
        }
    }

    fun setTimePickerVisibility(value: Boolean) {
        _timePickerVisibility.value = value
    }

    fun unsetErr() {
        _errorMessage.value = ""
    }

    fun showChangeOpponentSheet() {
        _changeOpponentVisibility.value = true
    }

    fun hideChangeOpponentSheet() {
        _changeOpponentVisibility.value = false
    }

    fun setChosenOpponent(newOpponent: Team){
        val chosenEvent = _chosenEventToEdit.value
        if(chosenEvent != null && chosenEvent is Match){
            val modified = chosenEvent.copy(opponent = newOpponent)
            val list = _events.value.toMutableList()
            list[list.indexOf(chosenEvent)] = modified
            saveModified(event = modified, list)
        }
    }

    fun setEventLocation(event: Event, newLocation: LatLng) {
        val list = _events.value.toMutableList()
        val index = list.indexOfFirst { it.id == event.id }
        println(">>>>>>>>>>>>>>>>>>>>>>${event.id}")
        println(">>>>>>>>>>>>>>>>${index}")
        println(list)
        if (index != -1) {
            val modified = when (event) {
                is Match -> event.copy(latitude = newLocation.latitude, longitude = newLocation.longitude)
                is Training -> event.copy(latitude = newLocation.latitude, longitude = newLocation.longitude)
                else -> return
            }
            list[index] = modified
            println(">>>>>>><<<<< guardando")
            saveModified(modified, list)
        }
    }

    fun getEvents(teamId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = ""
            try {
                val trainings = teamEventRepository.getTeamTrainings(teamId)
                val matches = teamEventRepository.getTeamMatches(teamId)
                val eventList = (matches + trainings).toMutableList()
                _events.value = eventList
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

    fun getTeams(){
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = ""

            try {
                val response = teamRepository.getAllTeams()
                teamList.value = response ?: emptyList()
            } catch (e: Exception){
                if(e.message == "401"){
                    _logout.value = true
                } else {
                    _errorMessage.value =  e.message ?: context.getString(R.string.err_exception)
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun saveModified(event: Event, list: List<Event>){
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = ""
            println(">>>>>>>>>>><saving")
            try {
                val response = teamEventRepository.updateEvent(event)
                println(">>>>>>>>>>><<<actualizando lista")
                _events.value = list
            } catch (e: Exception){
                if(e.message == "401"){
                    _logout.value = true
                } else {
                    _errorMessage.value =  e.message ?: context.getString(R.string.err_exception)
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    private var filterJob: Job? = null

    fun filterTeams() {
        filterJob?.cancel()
        filterJob = viewModelScope.launch {
            delay(300)
            val newFilteredList = teamList.value.filter {
                it.name.contains(teamName.value, ignoreCase = true)
            }
            _filteredTeamList.value = newFilteredList
            if (_filteredTeamList.value != newFilteredList) {
                _filteredTeamList.value = newFilteredList
            }
        }
    }

    fun deleteEvent(event: Event) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = ""
            try {
                val response = teamEventRepository.deleteEvent(event)
                val list = _events.value.toMutableList()
                list.removeAt(list.indexOf(event))
                _events.value = list
            } catch (e:Exception){
                if(e.message == "401"){
                    _logout.value = true
                } else {
                    _errorMessage.value =  e.message ?: context.getString(R.string.err_exception)
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
}