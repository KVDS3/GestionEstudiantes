package com.kevin.gestionestudiantes.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevin.gestionestudiantes.data.model.Estudiante
import com.kevin.gestionestudiantes.data.model.EstudianteRequest
import com.kevin.gestionestudiantes.data.repository.EstudianteRepository
import kotlinx.coroutines.launch

class EstudianteViewModel : ViewModel() {
    private val repository = EstudianteRepository()

    private val _estudiantes = mutableStateOf<List<Estudiante>>(emptyList())
    val estudiantes: State<List<Estudiante>> = _estudiantes

    private val _estudianteSeleccionado = mutableStateOf<Estudiante?>(null)
    val estudianteSeleccionado: State<Estudiante?> = _estudianteSeleccionado

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    private val _operacionExitosa = mutableStateOf(false)
    val operacionExitosa: State<Boolean> = _operacionExitosa

    init {
        cargarEstudiantes()
    }

    fun cargarEstudiantes() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            repository.obtenerEstudiantes()
                .onSuccess { lista ->
                    _estudiantes.value = lista
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Error al cargar estudiantes"
                }
            _isLoading.value = false
        }
    }

    fun cargarEstudiante(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            repository.obtenerEstudiante(id)
                .onSuccess { estudiante ->
                    _estudianteSeleccionado.value = estudiante
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Error al cargar estudiante"
                }
            _isLoading.value = false
        }
    }

    fun crearEstudiante(nombre: String, edad: Int, carrera: String, promedio: Double) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _operacionExitosa.value = false

            val nuevoEstudiante = EstudianteRequest(
                nombre = nombre,
                edad = edad,
                carrera = carrera,
                promedio = promedio
            )

            repository.crearEstudiante(nuevoEstudiante)
                .onSuccess {
                    _operacionExitosa.value = true
                    cargarEstudiantes()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Error al crear estudiante"
                }
            _isLoading.value = false
        }
    }

    fun actualizarEstudiante(id: Int, nombre: String, edad: Int, carrera: String, promedio: Double) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _operacionExitosa.value = false

            val estudianteActualizado = EstudianteRequest(
                nombre = nombre,
                edad = edad,
                carrera = carrera,
                promedio = promedio
            )

            repository.actualizarEstudiante(id, estudianteActualizado)
                .onSuccess {
                    _operacionExitosa.value = true
                    cargarEstudiantes()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Error al actualizar estudiante"
                }
            _isLoading.value = false
        }
    }

    fun eliminarEstudiante(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            repository.eliminarEstudiante(id)
                .onSuccess {
                    cargarEstudiantes()
                }
                .onFailure { exception ->
                    _error.value = exception.message ?: "Error al eliminar estudiante"
                }
            _isLoading.value = false
        }
    }

    fun limpiarError() {
        _error.value = null
    }

    fun resetearOperacionExitosa() {
        _operacionExitosa.value = false
    }

    fun limpiarEstudianteSeleccionado() {
        _estudianteSeleccionado.value = null
    }
}