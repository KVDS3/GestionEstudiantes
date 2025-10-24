package com.kevin.gestionestudiantes.data.model

import com.google.gson.annotations.SerializedName

data class Estudiante(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("nombre")
    val nombre: String = "",

    @SerializedName("edad")
    val edad: Int = 0,

    @SerializedName("carrera")
    val carrera: String = "",

    @SerializedName("promedio")
    val promedio: Double = 0.0,

    @SerializedName("fecha_registro")
    val fechaRegistro: String = ""
)

data class EstudianteRequest(
    val nombre: String,
    val edad: Int,
    val carrera: String,
    val promedio: Double
)