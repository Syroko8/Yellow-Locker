package com.example.nicolaspuebla_proyecto_final_android.data.model.dto

data class UserSignUp(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val disabled: Boolean = false,
    val birthDate: String
)
