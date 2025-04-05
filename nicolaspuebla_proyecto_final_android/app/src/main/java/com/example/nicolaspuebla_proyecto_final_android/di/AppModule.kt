package com.example.laboratorio_b.di

import com.example.nicolaspuebla_proyecto_final_android.data.repositories.AuthRepository
import com.example.nicolaspuebla_proyecto_final_android.data.repositories.UserRepository
import com.example.nicolaspuebla_proyecto_final_android.utils.SignUpData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
    fun provideSignUpData(): SignUpData{
        return SignUpData()
    }

}