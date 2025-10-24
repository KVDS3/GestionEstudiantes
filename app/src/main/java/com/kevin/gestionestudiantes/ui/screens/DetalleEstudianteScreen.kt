package com.kevin.gestionestudiantes.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kevin.gestionestudiantes.data.model.Estudiante

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleEstudianteScreen(
    estudiante: Estudiante?,
    isLoading: Boolean,
    onBackClick: () -> Unit,
    onEditarClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Estudiante") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                },
                actions = {
                    if (estudiante != null) {
                        IconButton(onClick = { onEditarClick(estudiante.id) }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                estudiante == null -> {
                    Text(
                        text = "Estudiante no encontrado",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        InfoItem(
                            label = "Nombre completo",
                            value = estudiante.nombre
                        )
                        InfoItem(
                            label = "Edad",
                            value = estudiante.edad.toString()
                        )
                        InfoItem(
                            label = "Carrera",
                            value = estudiante.carrera
                        )
                        InfoItem(
                            label = "Promedio",
                            value = estudiante.promedio.toString()
                        )
                        InfoItem(
                            label = "Fecha de registro",
                            value = estudiante.fechaRegistro
                        )
                        InfoItem(
                            label = "ID del estudiante",
                            value = estudiante.id.toString()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InfoItem(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
    }
}