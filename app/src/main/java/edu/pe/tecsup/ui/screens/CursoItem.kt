package edu.pe.tecsup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import edu.pe.tecsup.data.model.Curso
import edu.pe.tecsup.data.model.EstadoCurso

@Composable
fun CursoItem(
    curso: Curso,
    onEdit: (Curso) -> Unit,
    onDelete: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = curso.titulo, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = curso.descripcion, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(12.dp))
            Text("Créditos: ${curso.creditos}", style = MaterialTheme.typography.bodySmall)
            Text("Ciclo: ${curso.ciclo}", style = MaterialTheme.typography.bodySmall)
            Text("Carrera: ${curso.carrera}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                EstadoChip(estado = curso.estado)
                IconButton(onClick = { onEdit(curso) }) {
                    Icon(Icons.Filled.Edit, contentDescription = "Editar curso")
                }
                IconButton(onClick = { onDelete(curso.id) }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Eliminar curso")
                }
            }
        }
    }
}

@Composable
fun EstadoChip(estado: EstadoCurso) {
    val color = when (estado) {
        EstadoCurso.APROBADO -> Color(0xFF2E7D32)
        EstadoCurso.DESAPROBADO -> Color(0xFFC62828)
        EstadoCurso.PENDIENTE -> Color(0xFFF9A825)
    }

    AssistChip(
        onClick = {},
        label = { Text(text = estado.name) },
        colors = AssistChipDefaults.assistChipColors(
            labelColor = color,
            containerColor = color.copy(alpha = 0.15f)
        )
    )
}
