package com.example.nicolaspuebla_proyecto_final_android.ui.screens.signup2

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.UserSignUp
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.UserRepository
import com.example.nicolaspuebla_proyecto_final_android.utils.Hash
import com.example.nicolaspuebla_proyecto_final_android.utils.SignUpData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SignUp2ScreenViewModel @Inject constructor(
    private val signUpData: SignUpData,
    private val userRepository: UserRepository
): ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private val _signed = MutableStateFlow(false)
    val signed: StateFlow<Boolean> get() = _signed

    fun updatePasswd(passwd: String){
       signUpData.updatePasswd(passwd)
    }

    fun updatePasswdCheck(passwd: String){
        signUpData.updatePasswdCheck(passwd)
    }

    fun getPasswd(): StateFlow<String>{
        return signUpData.passwdTextFieldVal
    }

    fun getPasswdCheck(): StateFlow<String>{
        return signUpData.passwdTextField2Val
    }

    var passwdVisibility = mutableStateOf<Boolean>(false)
    var passwdVisibility2 = mutableStateOf<Boolean>(false)

    fun signUp(){
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val response = userRepository.signUp(buildUser())

                if (response != null){
                    _signed.value = true
                }
            } catch (e: HttpException){
                if(e.code() == 500){
                    _errorMessage.value = R.string.internal_server_err.toString()
                }
            } catch (e: Exception){
               _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun buildUser(): UserSignUp{
        return UserSignUp(
            name = signUpData.nameTextFieldVal.value,
            surname = signUpData.surnameTextFieldVal.value,
            email =  signUpData.mailTextFieldVal.value,
            password = Hash.hashPasswd(signUpData.passwdTextFieldVal.value),
            birthDate = signUpData.dateTextFieldVal.value.text.replace('-', '/')
        )
    }

    fun filledFields(): Boolean {
        return signUpData.filledFields()
    }
}
