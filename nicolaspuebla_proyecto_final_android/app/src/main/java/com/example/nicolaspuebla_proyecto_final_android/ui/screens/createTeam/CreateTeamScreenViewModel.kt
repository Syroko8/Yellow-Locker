package com.example.nicolaspuebla_proyecto_final_android.ui.screens.createTeam

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Locality
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.TeamCreation
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.LocalityRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel para la pantalla de creación de equipos.
 *
 * @property teamRepository Repositorio responsable de las operaciones relacionadas con equipos.
 * @property context Contexto de la aplicación para acceder a recursos como strings.
 * @property localityRepository Repositorio responsable de las localidades.
 */
@HiltViewModel
class CreateTeamScreenViewModel @Inject constructor(
    private val teamRepository: TeamRepository,
    @ApplicationContext private val context: Context,
    private val localityRepository: LocalityRepository
): ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _localityList = MutableStateFlow<List<Locality>>(emptyList())
    val localityList: StateFlow<List<Locality>> get() = _localityList

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _logout = MutableStateFlow(false)
    val logout: StateFlow<Boolean> get() = _logout

    var nameTextFieldValue = mutableStateOf("")

    var selectedLocality = mutableStateOf<Locality?>(null)

    var sportTextFieldValue = mutableStateOf("")

    var localitySheetVisibility = mutableStateOf(false)

    var logoSelected = mutableStateOf(false)

    var searchBarQuery = mutableStateOf("")

    var filteredLocalityList = mutableStateOf<List<Locality>>(emptyList())

    var creationResult = mutableStateOf(false)

    fun setErrMsg(msg: String){
        _errorMessage.value = msg
    }

    private var filterJob: Job? = null

    /**
     * Filtra la lista de localidades basándose en la consulta ingresada en la barra de búsqueda.
     */
    fun filterLocalities() {
        filterJob?.cancel()
        filterJob = viewModelScope.launch {
            delay(300)
            val newFilteredList = _localityList.value.filter {
                it.name.contains(searchBarQuery.value, ignoreCase = true)
            }
            filteredLocalityList.value = newFilteredList
            println(filteredLocalityList)
        }
    }

    /**
     * Envía los datos del nuevo equipo al repositorio para su creación.
     *
     * @param userId Identificador del usuario que crea el equipo.
     */
    fun createTeam(userId: Long){
        viewModelScope.launch {
            _errorMessage.value = ""
            _isLoading.value = true

            try {
                if(
                    nameTextFieldValue.value != "" &&
                    selectedLocality.value != null &&
                    sportTextFieldValue.value != ""
                ){
                    val response = teamRepository.createTeam(
                        TeamCreation(userId, nameTextFieldValue.value, selectedLocality.value!!, sportTextFieldValue.value)
                    )
                } else {
                    throw  Exception(context.getString(R.string.must_fill))
                }

                nameTextFieldValue.value = ""
                selectedLocality.value = null
                sportTextFieldValue.value = ""
                logoSelected.value = false
                creationResult.value = true
            } catch (e:Exception) {
                if (e.message == "401") {
                    _logout.value = true
                } else {
                    _errorMessage.value = e.message ?: context.getString(R.string.internal_server_err)
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Recupera la lista de localidades desde el repositorio.
     */
    fun getLocalities(){
        viewModelScope.launch {
            _errorMessage.value = ""
            _isLoading.value = true

            try {
                val result = localityRepository.getTeamLocalities()
                result?.let { _localityList.value = it.localities }
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
}