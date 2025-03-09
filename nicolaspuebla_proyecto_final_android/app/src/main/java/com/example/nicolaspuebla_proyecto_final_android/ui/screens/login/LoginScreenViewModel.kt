package com.example.nicolaspuebla_proyecto_final_android.ui.screens.login

import android.text.BoringLayout
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(

): ViewModel() {

    var mailTextFieldVal = mutableStateOf<String>("")
    var passwdTextFieldVal= mutableStateOf<String>("")
    var passwdVisibility = mutableStateOf<Boolean>(false)
}