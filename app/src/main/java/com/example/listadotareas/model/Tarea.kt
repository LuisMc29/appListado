package com.example.listadotareas.model

import java.util.UUID

data class Tarea(
    var id: String = UUID.randomUUID().toString(), // Mantiene la generación automática de ID
    var nombre: String = "",
    var descripcion: String = "",
    var fecha: String = ""
) {
    constructor() : this("", "", "", "") // Constructor sin argumentos
}

