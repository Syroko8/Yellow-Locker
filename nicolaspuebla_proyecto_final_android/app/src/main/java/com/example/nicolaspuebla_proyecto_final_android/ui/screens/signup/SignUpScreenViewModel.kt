package com.example.nicolaspuebla_proyecto_final_android.ui.screens.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(

): ViewModel() {

    var nameTextFieldVal = mutableStateOf<String>("")
    var surnameTextFieldVal = mutableStateOf<String>("")
    var secondSurnameTextFieldVal = mutableStateOf<String>("")
    var mailTextFieldVal = mutableStateOf<String>("")
    var dateTextFieldVal = mutableStateOf<TextFieldValue>(TextFieldValue(text = ""))
    var passwdTextFieldVal= mutableStateOf<String>("")
    var passwdVisibility = mutableStateOf<Boolean>(false)
    var passwdTextFiel2dVal= mutableStateOf<String>("")
    var passwdVisibility2 = mutableStateOf<Boolean>(false)

}