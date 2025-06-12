package com.example.listadotareas.adapter
import android.view.LayoutInflater
import com. example.listadotareas.model.Tarea
import androidx. recyclerview. widget. RecyclerView
import android. view. View
import android.view.ViewGroup
import android. widget. TextView
import com. example. listadotareas. R
import android. widget. Button
class TareaAdapter(
    private val onEditar: (Tarea) -> Unit,
    private val onEliminar: (Tarea) -> Unit
) : RecyclerView.Adapter<TareaAdapter.TareaViewHolder>() {

    private val tareas = mutableListOf<Tarea>()

    inner class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.textNombre)
        val descripcion: TextView = itemView.findViewById(R.id.textDescripcion)
        val fecha: TextView = itemView.findViewById(R.id.textFecha)
        val btnEditar: Button = itemView.findViewById(R.id.btnEditar)
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarea, parent, false)
        return TareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = tareas[position]
        holder.nombre.text = tarea.nombre
        holder.descripcion.text = tarea.descripcion
        holder.fecha.text = tarea.fecha

        holder.btnEditar.setOnClickListener {
            onEditar(tarea)
        }

        holder.btnEliminar.setOnClickListener {
            onEliminar(tarea)
        }
    }

    override fun getItemCount(): Int = tareas.size

    fun actualizarLista(nuevaLista: List<Tarea>) {
        tareas.clear()
        tareas.addAll(nuevaLista)
        notifyDataSetChanged()
    }
}
