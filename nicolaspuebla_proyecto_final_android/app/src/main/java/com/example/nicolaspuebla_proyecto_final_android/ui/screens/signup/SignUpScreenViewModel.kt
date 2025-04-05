package com.example.nicolaspuebla_proyecto_final_android.ui.screens.signup

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.nicolaspuebla_proyecto_final_android.utils.SignUpData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val signUpData: SignUpData
): ViewModel() {

    fun updateName(name: String){
        signUpData.updateName(name)
    }

    fun updateSurname(surname: String){
        signUpData.updateSurname(surname)
    }

    fun updateMail(mail: String){
        signUpData.updateMail(mail)
    }

    fun updateDate(date: TextFieldValue){
        signUpData.updateDate(date)
    }

    fun getName(): StateFlow<String> {
        return signUpData.nameTextFieldVal
    }

    fun getSurname(): StateFlow<String> {
        return signUpData.surnameTextFieldVal
    }

    fun getMail(): StateFlow<String> {
        return signUpData.mailTextFieldVal
    }

    fun getDate(): StateFlow<TextFieldValue> {
        return signUpData.dateTextFieldVal
    }

}