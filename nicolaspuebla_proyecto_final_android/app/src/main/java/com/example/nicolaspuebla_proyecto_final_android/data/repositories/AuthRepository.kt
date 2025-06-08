package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginRequest
import com.example.nicolaspuebla_proyecto_final_android.data.model.auth.LoginResponse
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.MobileUser
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repositorio a través del cual los view models pueden ejecutar las peticiones necesarias al microservicio sobre la autenticación
 * de usuarios.
 */
class AuthRepository {

    /**
     * Función que ejecuta la peción para realizar un login mediante el servicio.
     *
     * @param request Información para el login.
     * @return La respuesta del servidor al login, conteniendo el token e información de usuario.
     */
    @Throws(Exception::class)
    suspend fun login(request: LoginRequest): LoginResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val call = RetrofitInstance.yellowLockerAuth.login(request)
                val response = call.execute()

                if (response.isSuccessful) {
                    response.body()
                } else if(response.code() == 401){
                    throw Exception("401")
                } else {
                    println("Login failed with code: ${response.code()} and message: ${response.message()}")
                    null
                }
            } catch (e: Exception) {
                println("Login request failed: ${e.message}")
                null
            }
        }
    }

    /**
     * Función que ejecuta la petición de logout mediante el servicio.
     *
     * @param token Token a dar de baja.
     */
    suspend fun logout(token: String = SessionManager.bearerToken?:""): Unit{
        return withContext(Dispatchers.IO){
            try{
                val call = token.let { RetrofitInstance.yellowLockerAuth.logout(token)}
                val response = call.execute()

                if (response.isSuccessful){
                    response.body()
                } else{
                    println("Logout failed with code: ${response.code()} and message: ${response.message()}")
                    throw Exception(response.message())
                }
            } catch (e: Exception) {
                println("Logout request failed: ${e.message}")
                throw Exception(e.message)
            }
        }
    }

    /**
     * Función que ejecuta la comprobación de un token a través del servicio.
     *
     * @param userId Identificador del usuario asociado al token.
     * @param token Token a comprobar.
     * @return Información sobre el usuario, será nulo si el resultado de la comprobación es negativo.
     */
    suspend fun checkToken(userId: Long, token: String): MobileUser?{
        return withContext(Dispatchers.IO){
            try{
                val call = RetrofitInstance.yellowLockerAuth.tokenCheck(userId, token)
                val response = call.execute()

                if (response.isSuccessful){
                    response.body()?.user
                } else{
                    println(response.message())
                    throw Exception(response.message())
                }
            } catch (e: Exception) {
                println("Logout request failed: ${e.message}")
                throw Exception(e.message)
            }
        }
    }
}