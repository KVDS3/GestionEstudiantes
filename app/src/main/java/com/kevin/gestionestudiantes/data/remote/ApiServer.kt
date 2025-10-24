package com.kevin.gestionestudiantes.data.remote

import com.kevin.gestionestudiantes.data.model.Estudiante
import com.kevin.gestionestudiantes.data.model.EstudianteRequest
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("estudiantes/")
    suspend fun obtenerEstudiantes(
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 100
    ): Response<List<Estudiante>>

    @GET("estudiantes/{id}")
    suspend fun obtenerEstudiante(@Path("id") id: Int): Response<Estudiante>

    @POST("estudiantes/")
    suspend fun crearEstudiante(@Body estudiante: EstudianteRequest): Response<Estudiante>

    @PUT("estudiantes/{id}")
    suspend fun actualizarEstudiante(
        @Path("id") id: Int,
        @Body estudiante: EstudianteRequest
    ): Response<Estudiante>

    @DELETE("estudiantes/{id}")
    suspend fun eliminarEstudiante(@Path("id") id: Int): Response<Unit>
}