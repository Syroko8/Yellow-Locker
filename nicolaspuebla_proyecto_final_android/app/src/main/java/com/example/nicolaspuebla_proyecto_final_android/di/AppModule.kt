package com.example.laboratorio_b.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.nicolaspuebla_proyecto_final_android.data.preferences.DataStoreProvider
import com.example.nicolaspuebla_proyecto_final_android.data.preferences.PreferencesRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.AssignedPositionRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.AuthRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.LocalityRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamEventRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamPositionRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.TeamRolRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.UserRepository
import com.example.nicolaspuebla_proyecto_final_android.utils.SignUpData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository{
        return AuthRepository()
    }

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository{
        return UserRepository()
    }

    @Provides
    @Singleton
    fun provideTeamRepository(): TeamRepository{
        return TeamRepository()
    }

    @Provides
    @Singleton
    fun provideTeamRolRepository(): TeamRolRepository{
        return TeamRolRepository()
    }

    @Provides
    @Singleton
    fun provideSignUpData(): SignUpData{
        return SignUpData()
    }

    @Provides
    @Singleton
    fun provideTeamEventRepository(): TeamEventRepository{
        return TeamEventRepository()
    }

    @Provides
    @Singleton
    fun provideDataStoreProvider(@ApplicationContext context: Context): DataStoreProvider {
        return DataStoreProvider(context)
    }

    @Provides
    @Singleton
    fun providePreferencesRepository(dataStoreProvider: DataStoreProvider): PreferencesRepository {
        return PreferencesRepository(dataStoreProvider.dataStore)
    }

    @Provides
    @Singleton
    fun provideLocalityRepository(): LocalityRepository{
        return LocalityRepository()
    }

    @Provides
    @Singleton
    fun provideTeamPositionRepository(): TeamPositionRepository{
        return TeamPositionRepository()
    }

    @Provides
    @Singleton
    fun provideAssignedPositionRepository(): AssignedPositionRepository{
        return AssignedPositionRepository()
    }
}