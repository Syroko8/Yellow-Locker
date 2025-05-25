package com.example.nicolaspuebla_proyecto_final_android.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class DataStoreProvider(private val context: Context) {
    val dataStore: DataStore<Preferences> get() = context.dataStore
}