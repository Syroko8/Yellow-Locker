package com.example.nicolaspuebla_proyecto_final_android.utils

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object SessionManager {

    var user: MobileUser? = null

    var bearerToken:String? = null

    private val _logOut = MutableStateFlow(false)
    val logOut: StateFlow<Boolean> get() = _logOut

    fun setLogOut(value: Boolean) {
        _logOut.value = value
    }

    val logged: Boolean = false
}