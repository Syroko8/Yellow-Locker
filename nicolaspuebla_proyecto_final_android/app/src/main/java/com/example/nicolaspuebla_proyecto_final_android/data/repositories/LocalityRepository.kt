package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import com.example.nicolaspuebla_proyecto_final_android.data.model.dto.LocalityListResponse
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repositorio a través del cual los view models pueden ejecutar las peticiones necesarias al microservicio sobre la entidad Locality.
 */
class LocalityRepository {

    /**
     * Función que ejecuta la petición para obtener todas las localidades.
     *
     * @return Una lista con todas las localidades de la base de datos.
     */
    suspend fun getTeamLocalities(): LocalityListResponse?{
        return withContext(Dispatchers.IO){
            try {
                val call = RetrofitInstance.yellowLockerLocalityService.getLocalities()
                val response = call.execute()

                if(response.isSuccessful){
                    response.body()
                } else if(response.code() == 401){
                    throw Exception("401")
                } else {
                    throw Exception("Internal server error")
                }
            } catch (e:Exception){
                throw Exception(e.message)
            }
        }
    }
}