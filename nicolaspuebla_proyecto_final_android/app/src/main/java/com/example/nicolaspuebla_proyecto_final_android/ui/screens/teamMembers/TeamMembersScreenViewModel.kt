package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamMembers

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.TeamPosition
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.MemberListElement
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.AssignedPositionRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamPositionRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamRolRepository
import com.example.nicolaspuebla_proyecto_final_android.ui.components.getPosition
import com.example.nicolaspuebla_proyecto_final_android.utils.TeamRoles
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel para la pantalla de miembros del equipo.
 *
 * @property teamRepository Repositorio para operaciones de equipo.
 * @property teamRolRepository Repositorio para gestión de roles.
 * @property teamPositionRepository Repositorio para operaciones con las posiciones del equipo.
 * @property assignedPositionRepository Repositorio para asignación de posiciones.
 * @property context Contexto de aplicación para recursos.
 */
@HiltViewModel
class TeamMembersScreenViewModel @Inject constructor(
    private val teamRepository: TeamRepository,
    private val teamRolRepository: TeamRolRepository,
    private val teamPositionRepository: TeamPositionRepository,
    private val assignedPositionRepository: AssignedPositionRepository,
    @ApplicationContext private val context: Context
): ViewModel(){

    var selectedMember = mutableStateOf<MemberListElement?>(null)

    var modifyMemberSheetVisibility = mutableStateOf(false)

    var newRol = mutableStateOf<TeamRoles?>(null)

    var newPosition = mutableStateOf<TeamPosition?>(null)

    var creatingNewPosition = mutableStateOf(false)

    var createdPositionName = mutableStateOf("")

    private val _team = MutableStateFlow<Team?>(null)
    val team : StateFlow<Team?> get() = _team

    private val _members = MutableStateFlow<List<MemberListElement>>(emptyList())
    val members: StateFlow<List<MemberListElement>> get() = _members

    private val _positions = MutableStateFlow<List<TeamPosition>>(emptyList())
    val positions: StateFlow<List<TeamPosition>> get() = _positions

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _logout = MutableStateFlow(false)
    val logout: StateFlow<Boolean> get() = _logout

    fun unsetError() {
        _errorMessage.value = ""
    }

    /**
     * Obtiene los miembros de un equipo.
     *
     * @param teamId Identificador del equipo.
     */
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
                    _errorMessage.value = e.message?: context.getString(R.string.internal_server_err)
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Obtiene la información detallada de un equipo.
     *
     * @param teamId Identificador del equipo.
     */
    fun getTeam(teamId: Long){
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = ""
            try {
                val response = teamRepository.getTeam(teamId)
                _team.value = response
            } catch (e:Exception) {
                if(e.message == "401"){
                    _logout.value = true
                    _errorMessage.value = context.getString(R.string.no_valid_token)
                } else {
                    _errorMessage.value = e.message?: context.getString(R.string.internal_server_err)
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Obtiene las posiciones disponibles en un equipo.
     *
     * @param teamId Identificador del equipo.
     */
    fun getTeamPositions(teamId: Long){
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = ""
            try {
                val response = teamPositionRepository.getTeamPositions(teamId)
                if (response != null) {
                    _positions.value = response
                }
            } catch (e:Exception) {
                if(e.message == "401"){
                    _logout.value = true
                    _errorMessage.value = context.getString(R.string.no_valid_token)
                } else {
                    _errorMessage.value = e.message?: context.getString(R.string.internal_server_err)
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Guarda los cambios realizados a un miembro.
     *
     * @param teamId Identificador del equipo.
     */
    fun saveChanges(teamId: Long){
        viewModelScope.launch {
            _errorMessage.value = ""
            _isLoading.value = true

            try {
                if(newRol.value?.name != selectedMember.value?.rolType){
                    newRol.value?.let { teamRolRepository.changeRol(selectedMember.value?.user?.id!!, teamId, it) }
                }
                newPosition.value?.let {
                    if(it.name != getPosition(selectedMember.value!!, teamId)){
                        assignedPositionRepository.changeAssignedPosition(selectedMember.value?.user?.id!!, teamId, it.id)
                    }
                }
                getMembers(teamId)
                newRol.value = null
                newPosition.value = null
            } catch (e:Exception){
                newRol.value = null
                newPosition.value = null
                if(e.message == "401"){
                    _logout.value = true
                    _errorMessage.value = context.getString(R.string.no_valid_token)
                } else {
                    _errorMessage.value = e.message?: context.getString(R.string.internal_server_err)
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Crea una nueva posición en el equipo.
     *
     * @param teamId Ientificaor del equipo.
     */
    fun createTeamPosition(teamId: Long){
        viewModelScope.launch {
            _errorMessage.value = ""
            _isLoading.value = true

            try {
                teamPositionRepository.createTeamPosition(teamId, createdPositionName.value)
                val response = teamRepository.getTeam(teamId)
                _team.value = response
                getTeamPositions(teamId)
            } catch (e: Exception){
                if(e.message == "401"){
                    _logout.value = true
                    _errorMessage.value = context.getString(R.string.no_valid_token)
                } else {
                    _errorMessage.value = e.message?: context.getString(R.string.internal_server_err)
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Elimina una posición del equipo.
     *
     * @param position Posición a eliminar.
     */
    fun deletePosition(position: TeamPosition){
        viewModelScope.launch {
            _errorMessage.value = ""
            _isLoading.value = true

            try {
                teamPositionRepository.deleteTeamPosition(position.id)
                val positions = _positions.value.toMutableList()
                positions.remove(position)
                _positions.value = positions
            } catch (e: Exception) {

                if (e.message == "401") {
                    _logout.value = true
                    _errorMessage.value = context.getString(R.string.no_valid_token)
                } else if (e.message == context.getString(R.string.constraint_err_code)) {
                    _errorMessage.value = context.getString(R.string.constraint_err)
                }else {
                    _errorMessage.value = e.message?: context.getString(R.string.internal_server_err)
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Expulsa a un miembro del equipo.
     */
    fun expelMember(){
        viewModelScope.launch {
            _errorMessage.value = ""
            _isLoading.value = true

            try {
                _team.value?.id?.let { teamRepository.leaveTeam(selectedMember.value?.user?.id!!, it) }
                val members = _members.value.toMutableList()
                members.remove(selectedMember.value)
                _members.value = members
            } catch (e: Exception){
                if(e.message == "401"){
                    _logout.value = true
                    _errorMessage.value = context.getString(R.string.no_valid_token)
                } else {
                    _errorMessage.value = e.message?: context.getString(R.string.internal_server_err)
                }
            } finally {
                _isLoading.value = true
            }
        }
    }
}

