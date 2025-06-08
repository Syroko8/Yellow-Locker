package com.example.nicolaspuebla_proyecto_final_android.data.repositories

import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.AssignedPosition
import com.example.nicolaspuebla_proyecto_final_android.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repositorio a través del cual los view models pueden ejecutar las peticiones necesarias al microservicio sobre la entidad
 * AssignedPosition.
 */
class AssignedPositionRepository {

    /**
     * Función que ejecuta la petición para realizar un cambio de posición para un miembro.
     *
     * @param userId Identificador del usuario afectado.
     * @param teamId Identificador del equipo.
     * @param newAssignedPositionId Nueva posición para el usuario.
     * @return La nueva posición asignada.
     */
    suspend fun changeAssignedPosition(userId: Long, teamId: Long,  newAssignedPositionId: Long): AssignedPosition?{
        return withContext((Dispatchers.IO)){
            try {
                val call = RetrofitInstance.yellowLockerAssignedPositionService.changeAssignedPosition(userId, teamId, newAssignedPositionId)
                val response = call.execute()

                if(response.isSuccessful){
                    response.body()
                } else if(response.code() == 401){
                    throw Exception("401")
                } else {
                    throw Exception("Internal server error")
                }
            }catch (e: Exception){
                throw Exception(e.message)
            }
        }
    }
}