package com.example.listadotareas.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.listadotareas.model.Tarea
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log

class TareasViewModel : ViewModel() {
    private val _tareas = MutableLiveData<List<Tarea>>(emptyList())
    val tareas: LiveData<List<Tarea>> get() = _tareas

    private val db = FirebaseFirestore.getInstance()
    private val tareasRef = db.collection("tareas")

/*
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
 */


    init {
        // Escuchar cambios en Firestore para actualizar la lista en tiempo real
        tareasRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.e("Firebase", "Error al obtener tareas", e)
                return@addSnapshotListener
            }
            val listaTareas = snapshot?.documents?.mapNotNull { it.toObject(Tarea::class.java)?.copy(id = it.id) } ?: emptyList()
            _tareas.value = listaTareas
        }
    }

    fun agregarTarea(tarea: Tarea) {
        tareasRef.add(tarea)
            .addOnSuccessListener { docRef ->
                Log.d("Firebase", "Tarea agregada con ID: ${docRef.id}")
            }
            .addOnFailureListener { e ->
                Log.e("Firebase", "Error al agregar tarea", e)
            }
    }

    fun eliminarTarea(id: String) {
        tareasRef.document(id).delete()
            .addOnSuccessListener { Log.d("Firebase", "Tarea eliminada") }
            .addOnFailureListener { e -> Log.e("Firebase", "Error al eliminar tarea", e) }
    }

    fun actualizarTarea(tareaEditada: Tarea) {
        tareasRef.document(tareaEditada.id).set(tareaEditada)
            .addOnSuccessListener { Log.d("Firebase", "Tarea actualizada") }
            .addOnFailureListener { e -> Log.e("Firebase", "Error al actualizar tarea", e) }
    }

    fun obtenerTareaPorId(id: String): Tarea? {
        return _tareas.value?.find { it.id == id }
    }
}

