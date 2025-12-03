package edu.pe.tecsup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.pe.tecsup.ui.screens.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCursoScreen(
    onNavigateBack: () -> Unit,
    viewModel: CursoViewModel
) {

    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var creditos by remember { mutableStateOf("") }
    var ciclo by remember { mutableStateOf("") }
    var carrera by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar Curso") },
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
                label = { Text("Título del Curso") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = creditos,
                onValueChange = { if (it.all { c -> c.isDigit() }) creditos = it },
                label = { Text("Créditos") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = ciclo,
                onValueChange = { if (it.all { c -> c.isDigit() }) ciclo = it },
                label = { Text("Ciclo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = carrera,
                onValueChange = { carrera = it },
                label = { Text("Carrera") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            val allFieldsFilled = titulo.isNotBlank() &&
                    descripcion.isNotBlank() &&
                    creditos.isNotBlank() &&
                    ciclo.isNotBlank() &&
                    carrera.isNotBlank()

            Button(
                onClick = {
                    viewModel.addCurso(
                        titulo = titulo,
                        descripcion = descripcion,
                        creditos = creditos.toInt(),
                        ciclo = ciclo.toInt(),
                        carrera = carrera
                    )
                    onNavigateBack()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = allFieldsFilled
            ) {
                Text("Guardar Curso")
            }
        }
    }
}
