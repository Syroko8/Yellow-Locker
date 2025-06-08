package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.UserSignUp
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repositorio a través del cual los view models pueden ejecutar las peticiones necesarias al microservicio sobre la entidad User.
 */
class UserRepository {

    /**
     * Función que obtiene la información de un usuario a través del servicio.
     *
     * @param id Identificador del usuario.
     * @return Información del usuario.
     */
    suspend fun getUser(id: Long): MobileUser?{
        return withContext(Dispatchers.IO) {
            try {
                val call = RetrofitInstance.yellowLockerUserService.getUser(id)
                val response = call.execute()

                if (response.isSuccessful) {
                    response.body()
                } else if(response.code() == 401){
                    throw Exception("401")
                } else {
                    println("Fetch failed with code: ${response.code()} and message: ${response.message()}")
                    throw Exception("Internal server error")
                }
            } catch (e: Exception) {
                println("Fetch request failed: ${e.message}")
                throw Exception(e.message)
            }
        }
    }

    /**
     * Función que ejecuta la petición de registro de usuario a través del servicio.
     *
     * @param user Información de registro de usuario.
     * @return Información del usuario registrado.
     */
    suspend fun signUp(user: UserSignUp): MobileUser?{
        return withContext(Dispatchers.IO) {
            try {
                val call = RetrofitInstance.yellowLockerUserService.signUp(user)
                val response = call.execute()

                if (response.isSuccessful) {
                    response.body()
                } else {
                    println("SignUp failed with code: ${response.code()} and message: ${response.message()}")
                    null
                }
            } catch (e: Exception) {
                println("SignUp request failed: ${e.message}")
                null
            }
        }
    }
}