package com.example.nicolaspuebla_proyecto_final_android.utils

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Event
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object LocationChoosingInfo {

    private val _event = MutableStateFlow<Event?>(null)
    val event: StateFlow<Event?> get() = _event

    private val _selectedMapPosition = MutableStateFlow<LatLng?>(null)
    val selectedMapPosition: StateFlow<LatLng?> get() = _selectedMapPosition

    private val _chosen = MutableStateFlow<Boolean>(false)
    val chosen: StateFlow<Boolean> get() = _chosen

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