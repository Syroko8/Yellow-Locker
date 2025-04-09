package com.example.nicolaspuebla_proyecto_final_android.utils

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.example.nicolaspuebla_proyecto_final_android.ui.components.TextFieldDateFormatter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Singleton

@Singleton
class SignUpData {
    private var _nameTextFieldVal = MutableStateFlow("")
    val nameTextFieldVal: StateFlow<String> get() = _nameTextFieldVal

    private var _surnameTextFieldVal = MutableStateFlow("")
    val surnameTextFieldVal: StateFlow<String> get() = _surnameTextFieldVal

    private var _mailTextFieldVal = MutableStateFlow("")
    val mailTextFieldVal: StateFlow<String> get() = _mailTextFieldVal

    private var _dateTextFieldVal = MutableStateFlow(
        TextFieldValue(
            text = TextFieldDateFormatter.format(TextFieldValue("")),
            selection = TextRange(0)
        )
    )
    val dateTextFieldVal: StateFlow<TextFieldValue> get() = _dateTextFieldVal

    private var _passwdTextFieldVal = MutableStateFlow("")
    val passwdTextFieldVal: StateFlow<String> get() = _passwdTextFieldVal

    private var _passwdTextField2Val = MutableStateFlow("")
    val passwdTextField2Val: StateFlow<String> get() = _passwdTextField2Val

    fun updateName(name: String){
        _nameTextFieldVal.value = name
    }

    fun updateSurname(surname: String){
        _surnameTextFieldVal.value = surname
    }

    fun updateMail(mail: String){
        _mailTextFieldVal.value = mail
    }

    fun updateDate(date: TextFieldValue){
        _dateTextFieldVal.value = date
    }

    fun updatePasswd(passwd: String){
        _passwdTextFieldVal.value = passwd
    }

    fun updatePasswdCheck(passwd: String){
        _passwdTextField2Val.value = passwd
    }

    fun filledFields(): Boolean {
        if(
            _nameTextFieldVal.value == "" ||
            _surnameTextFieldVal.value == "" ||
            _mailTextFieldVal.value == "" ||
            _dateTextFieldVal.value.text == TextFieldDateFormatter.format(TextFieldValue("")) ||
            _passwdTextFieldVal.value == ""
            ){
            return false
        } else{
            return true
        }
    }
}