package com.example.nicolaspuebla_proyecto_final_android.ui.screens.login

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginRequest
import com.example.nicolaspuebla_proyecto_final_android.data.preferences.PreferencesRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.AuthRepository
import com.example.nicolaspuebla_proyecto_final_android.utils.Hash
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    @ApplicationContext private val context: Context,
    private val preferencesRepository: PreferencesRepository
): ViewModel() {

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _logged = MutableStateFlow<Boolean>(false)
    val logged: StateFlow<Boolean> get() = _logged

    private  val _mustFill = MutableStateFlow<Boolean>(false)
    val mustFill: StateFlow<Boolean> get() = _mustFill

    var mailTextFieldVal = mutableStateOf<String>("")
    var passwdTextFieldVal= mutableStateOf<String>("")
    var passwdVisibility = mutableStateOf<Boolean>(false)

    fun login(){
        viewModelScope.launch {
            try {
                _loading.value = true
                _errorMessage.value = ""
                val loginRequest = LoginRequest(mailTextFieldVal.value, Hash.hashPasswd(passwdTextFieldVal.value))
                println(">>>>>>>>>>><Mandando request")
                val response = authRepository.login(loginRequest)
                println(">>>>>>>>>>><response recivida")

                if((response != null)){
                    _logged.value = true
                    SessionManager.user = response.user
                    SessionManager.bearerToken = response.token

                    preferencesRepository.saveToken(response.token)
                    preferencesRepository.saveUserId(response.user.id)
                } else {
                    _errorMessage.value = context.getString(R.string.failedLogin)
                }
            } catch (e:Exception){
                if(e.message == "401"){
                    _errorMessage.value = context.getString(R.string.bad_auth_param)

                } else{
                    _errorMessage.value = e.message?:""
                }
            } finally {
                _loading.value = false
            }
        }
    }

    fun unsetError() {
        _errorMessage.value = ""
    }

    fun setMustFill(value: Boolean) {
        _mustFill.value = value
    }

    fun checkToken(userId:Long?, token: String) {
        if(userId != null && token != ""){
            viewModelScope.launch {
                _errorMessage.value = ""
                _loading.value = true
                try{
                    val response = authRepository.checkToken(userId, token)
                    if(response != null){
                        SessionManager.user = response
                        SessionManager.bearerToken = token
                        _logged.value = true
                    }
                } catch (e: Exception){
                    if(e.message == "401"){
                        _errorMessage.value = context.getString(R.string.bad_auth_param)
                    } else{
                        _errorMessage.value = e.message?: context.getString(R.string.internal_server_err)
                    }
                } finally {
                    _loading.value = false
                }
            }
        }
    }
}