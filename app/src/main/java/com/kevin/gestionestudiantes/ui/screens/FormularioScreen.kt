package com.kevin.gestionestudiantes.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kevin.gestionestudiantes.data.model.Estudiante

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioScreen(
    estudiante: Estudiante?,
    isLoading: Boolean,
    onBackClick: () -> Unit,
    onGuardarClick: (String, Int, String, Double) -> Unit
) {
    var nombre by remember { mutableStateOf(estudiante?.nombre ?: "") }
    var edad by remember { mutableStateOf(estudiante?.edad?.toString() ?: "") }
    var carrera by remember { mutableStateOf(estudiante?.carrera ?: "") }
    var promedio by remember { mutableStateOf(estudiante?.promedio?.toString() ?: "") }

    val esValido = nombre.isNotBlank() &&
            edad.toIntOrNull() != null &&
            carrera.isNotBlank() &&
            promedio.toDoubleOrNull() != null

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (estudiante == null) "Nuevo Estudiante"
                        else "Editar Estudiante"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (esValido) {
                        onGuardarClick(
                            nombre,
                            edad.toInt(),
                            carrera,
                            promedio.toDouble()
                        )
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Guardar")
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre completo") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = edad,
                onValueChange = { edad = it },
                label = { Text("Edad") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            OutlinedTextField(
                value = carrera,
                onValueChange = { carrera = it },
                label = { Text("Carrera") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = promedio,
                onValueChange = { promedio = it },
                label = { Text("Promedio") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Decimal
                )
            )

            if (!esValido && (nombre.isNotEmpty() || edad.isNotEmpty() || carrera.isNotEmpty() || promedio.isNotEmpty())) {
                Text(
                    text = "Por favor, completa todos los campos correctamente",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}