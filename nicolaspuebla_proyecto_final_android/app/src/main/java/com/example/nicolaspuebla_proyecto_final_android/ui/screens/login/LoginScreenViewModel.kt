package com.example.nicolaspuebla_proyecto_final_android.ui.screens.login

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.apiClases.MobileUserInfo
import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginRequest
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.AuthRepository
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
    @ApplicationContext private val context: Context

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

                val loginRequest = LoginRequest(mailTextFieldVal.value, passwdTextFieldVal.value)
                val response = authRepository.login(loginRequest)
                if((response != null)){
                    _logged.value = true
                    SessionManager.user = response.user
                    SessionManager.bearerToken = response.token
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
}