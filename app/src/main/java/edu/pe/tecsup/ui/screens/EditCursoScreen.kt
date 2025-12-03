package edu.pe.tecsup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.pe.tecsup.data.model.Curso
import edu.pe.tecsup.data.model.EstadoCurso

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCursoScreen(
    curso: Curso,
    onNavigateBack: () -> Unit,
    viewModel: CursoViewModel
) {
    var titulo by remember { mutableStateOf(curso.titulo) }
    var descripcion by remember { mutableStateOf(curso.descripcion) }
    var creditos by remember { mutableStateOf(curso.creditos.toString()) }
    var ciclo by remember { mutableStateOf(curso.ciclo.toString()) }
    var carrera by remember { mutableStateOf(curso.carrera) }
    var estado by remember { mutableStateOf(curso.estado) }
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Curso") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = creditos,
                onValueChange = { creditos = it },
                label = { Text("Créditos") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = ciclo,
                onValueChange = { ciclo = it },
                label = { Text("Ciclo") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = carrera,
                onValueChange = { carrera = it },
                label = { Text("Carrera") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = estado.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Estado") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    EstadoCurso.values().forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item.name) },
                            onClick = {
                                estado = item
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    val c = creditos.toIntOrNull() ?: 0
                    val ci = ciclo.toIntOrNull() ?: 0
                    viewModel.updateCurso(
                        id = curso.id,
                        titulo = titulo,
                        descripcion = descripcion,
                        creditos = c,
                        ciclo = ci,
                        carrera = carrera,
                        estado = estado
                    )
                    onNavigateBack()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = titulo.isNotBlank() &&
                        descripcion.isNotBlank() &&
                        carrera.isNotBlank() &&
                        creditos.isNotBlank() &&
                        ciclo.isNotBlank()
            ) {
                Text("Actualizar Curso")
            }
        }
    }
}
