package com.example.listadotareas.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com. example.listadotareas.model.Tarea

class TareasViewModel : ViewModel() {
    private val _tareas = MutableLiveData<List<Tarea>>(emptyList())
    val tareas: LiveData<List<Tarea>> get() = _tareas

    fun agregarTarea(tarea: Tarea) {
        val listaActual = _tareas.value ?: emptyList()
        _tareas.value = listaActual + tarea // Crea una nueva lista (inmutable)
    }

    fun eliminarTarea(id: String) {
        _tareas.value = _tareas.value?.filter { it.id != id }
    }

    fun actualizarTarea(tareaEditada: Tarea) {
        _tareas.value = _tareas.value?.map {
            if (it.id == tareaEditada.id) tareaEditada else it
        }
    }

    fun obtenerTareaPorId(id: String): Tarea? {
        return _tareas.value?.find { it.id == id }
    }
}

