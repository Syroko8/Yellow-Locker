package com.example.nicolaspuebla_proyecto_final_android.utils

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object SessionManager {

    val logged: Boolean = false

    var user: MobileUser? = null

    var bearerToken:String? = null

    private val _logOut = MutableStateFlow(false)
    val logOut: StateFlow<Boolean> get() = _logOut

    fun setLogOut(value: Boolean) {
        _logOut.value = value
    }

    private val _actualTeamRole = MutableStateFlow<TeamRoles?>(null)
    val actualTeamRole: StateFlow<TeamRoles?> get() = _actualTeamRole

    fun setTeamRole(value: TeamRoles) {
        _actualTeamRole.value = value
    }

    private val _actualTeamId = MutableStateFlow<Long?>(null)
    val actualTeamId: StateFlow<Long?> get() = _actualTeamId

    fun setTeamId(value: Long) {
        _actualTeamId.value = value
    }
}