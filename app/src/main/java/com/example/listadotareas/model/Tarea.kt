package com.example.listadotareas.model

import java.util.UUID

data class Tarea(
    var id: String = UUID.randomUUID().toString(),
    var nombre: String = "",
    var descripcion: String = "",
    var fecha: String = ""
) {
    constructor() : this("", "", "", "")
}

