package edu.pe.tecsup.data.model

data class Curso(
    val id: String = "",
    val titulo: String = "",
    val descripcion: String = "",
    val creditos: Int = 0,
    val ciclo: Int = 0,
    val carrera: String = "",
    val estado: EstadoCurso = EstadoCurso.PENDIENTE,
    val userId: String = ""
)

enum class EstadoCurso {
    APROBADO,
    DESAPROBADO,
    PENDIENTE
}
