package com.example.nicolaspuebla_proyecto_final_android.utils

import androidx.compose.runtime.mutableStateOf
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.example.nicolaspuebla_proyecto_final_android.ui.screens.createEvent.CreationInfo
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

enum class MapAction(){
    ChangeOpponent,
    ChooseOpponent
}

/**
 * Objeto empleado para almacenar información al usar MapScreen necesaria para otras vistas.
 */
object LocationChoosingInfo {

    private val _event = MutableStateFlow<Event?>(null)
    val event: StateFlow<Event?> get() = _event

    private val _selectedMapPosition = MutableStateFlow<LatLng?>(null)
    val selectedMapPosition: StateFlow<LatLng?> get() = _selectedMapPosition

    private val _chosen = MutableStateFlow<Boolean>(false)
    val chosen: StateFlow<Boolean> get() = _chosen

    var action = mutableStateOf<MapAction?>(null)

    var eventCreationInfo = mutableStateOf<CreationInfo?>(null)

    fun setEvent(event: Event){
        _event.value = event
    }

    fun setSelectedMapPosition(position: LatLng){
        _selectedMapPosition.value = position
    }

    fun setChosen(value: Boolean){
        _chosen.value = value
    }
}