package com.example.nicolaspuebla_proyecto_final_android.ui.screens.createEvent

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.EventCreation
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.MatchCreation
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.TrainingCreation
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

enum class EventType(){
    Match,
    Training
}

@HiltViewModel
class CreateEventScreenViewModel @Inject constructor(
    private val teamRepository: TeamRepository,
    private val teamEventRepository: TeamEventRepository,
    @ApplicationContext private val context: Context
): ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _logout = MutableStateFlow(false)
    val logout: StateFlow<Boolean> get() = _logout

    var date = mutableStateOf<OffsetDateTime?>(null)

    var opponent = mutableStateOf<Team?>(null)

    var location = mutableStateOf<LatLng?>(null)

    var showTeamChoosingSheet = mutableStateOf(false)

    var showDatePicker = mutableStateOf(false)

    var showTimePicker = mutableStateOf(false)

    private val teamList = MutableStateFlow<List<Team>>(emptyList())

    var filteredTeamList = mutableStateOf<List<Team>>(emptyList())

    var searchBarQuery = mutableStateOf("")

    var mapVisibility = mutableStateOf(false)

    var eventType = mutableStateOf<EventType>(EventType.Match)

    fun unsetErr(){
        _errorMessage.value = ""
    }

    fun saveEvent(teamId: Long){
        viewModelScope.launch {
            _errorMessage.value = ""
            _isLoading.value = true
            try {
                val newEvent: EventCreation = if(eventType.value == EventType.Match){
                    MatchCreation(
                        event_type = "match",
                        teamId = teamId,
                        opponent = opponent.value!!,
                        date = date.value.toString(),
                        latitude = location.value?.latitude!!,
                        longitude = location.value?.longitude!!,
                        ownGoals = 0,
                        opponentGoals = 0
                    )
                } else {
                    TrainingCreation(
                        event_type = "training",
                        teamId = teamId,
                        date = date.value.toString(),
                        latitude = location.value?.latitude!!,
                        longitude = location.value?.longitude!!
                    )
                }
                println(newEvent)
                val response = teamEventRepository.createEvent(newEvent)
                date.value = null
                opponent.value = null
                location.value = null
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
                it.name.contains(searchBarQuery.value, ignoreCase = true)
            }
            filteredTeamList.value = newFilteredList
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

    fun setCreationInfoLocationChoosing() {
        val eventInfo = CreationInfo(
            date.value,
            opponent.value,
            location.value
        )
        LocationChoosingInfo.eventCreationInfo.value = eventInfo
    }

    fun getLocationChoosingCreationInfo() {
        val info = LocationChoosingInfo.eventCreationInfo.value
        if(info != null){
            date.value = info.dateTime
            opponent.value = info.opponent
            location.value = info.location
        }
        LocationChoosingInfo.eventCreationInfo.value = null
    }
}

data class CreationInfo(
    val dateTime: OffsetDateTime?,
    val opponent: Team?,
    val location: LatLng?
)

