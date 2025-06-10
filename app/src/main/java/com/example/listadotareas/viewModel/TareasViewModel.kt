package com.example.listadotareas.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com. example.listadotareas.model.Tarea

class TareasViewModel : ViewModel() {
    private val _tareas = MutableLiveData<MutableList<Tarea>>(mutableListOf())
    val tareas: LiveData<List<Tarea>> get() = _tareas as LiveData<List<Tarea>>

    fun agregarTarea(tarea: Tarea) {
        _tareas.value?.add(tarea)
        _tareas.value = _tareas.value
    }
}
