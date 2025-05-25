package com.example.nicolaspuebla_proyecto_final_android.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
){
    // Valores almacenados.
    private val USERID = longPreferencesKey("userId")
    private val TOKEN = stringPreferencesKey("token")

    val tokenFlow: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[TOKEN] ?: ""
        }

    suspend fun saveToken(newToken: String) {
        dataStore.edit { preferences ->
            println(">>>>>>>>><<<Almacenando token")
            preferences[TOKEN] = newToken
        }
    }

    val userIdFlow: Flow<Long?> = dataStore.data
        .map { preferences ->
            preferences[USERID]
        }

    suspend fun saveUserId(userId: Long){
        dataStore.edit { preferences ->
            println(">>>>>>>>>>>Almacenando id")
            preferences[USERID] = userId
        }
    }

    suspend fun clearUserData() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}