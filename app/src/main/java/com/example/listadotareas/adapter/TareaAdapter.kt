package com.example.listadotareas.adapter
import android.view.LayoutInflater
import com. example.listadotareas.model.Tarea
import androidx. recyclerview. widget. RecyclerView
import android. view. View
import android.view.ViewGroup
import android. widget. TextView
import com. example. listadotareas. R


class TareaAdapter(private val tareas: List<Tarea>) : RecyclerView.Adapter<TareaAdapter.TareaViewHolder>() {

    class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre = itemView.findViewById<TextView>(R.id.textNombre)
        val descripcion = itemView.findViewById<TextView>(R.id.textDescripcion)
        val fecha = itemView.findViewById<TextView>(R.id.textFecha)
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
    }

    override fun getItemCount(): Int = tareas.size
}
