package com.kevin.gestionestudiantes.data.repository

import com.kevin.gestionestudiantes.data.model.Estudiante
import com.kevin.gestionestudiantes.data.model.EstudianteRequest
import com.kevin.gestionestudiantes.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EstudianteRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun obtenerEstudiantes(): Result<List<Estudiante>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.obtenerEstudiantes()
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun obtenerEstudiante(id: Int): Result<Estudiante> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.obtenerEstudiante(id)
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Estudiante no encontrado"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun crearEstudiante(estudiante: EstudianteRequest): Result<Estudiante> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.crearEstudiante(estudiante)
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Error al crear estudiante"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun actualizarEstudiante(id: Int, estudiante: EstudianteRequest): Result<Estudiante> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.actualizarEstudiante(id, estudiante)
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Error al actualizar estudiante"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun eliminarEstudiante(id: Int): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.eliminarEstudiante(id)
                if (response.isSuccessful) {
                    Result.success(Unit)
                } else {
                    Result.failure(Exception("Error al eliminar estudiante"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}