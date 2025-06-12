package com.example.listadotareas.model

import java.util.UUID

data class Tarea(
    val id: String = UUID.randomUUID().toString(),
    val nombre: String,
    val descripcion: String,
    val fecha: String
)
