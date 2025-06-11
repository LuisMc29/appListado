package com.example.listadotareas.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.listadotareas.R
import com.example.listadotareas.databinding.FragmentPendientesBinding
import com. example. listadotareas. viewModel. TareasViewModel
import androidx. fragment. app. activityViewModels
import androidx. recyclerview. widget. LinearLayoutManager
import com.example.listadotareas.adapter.TareaAdapter

class PendientesFragment : Fragment() {

    private var _binding: FragmentPendientesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TareasViewModel by activityViewModels()

    private lateinit var tareaAdapter: TareaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPendientesBinding.inflate(inflater, container, false)

        tareaAdapter = TareaAdapter(
            onEditar = { tarea ->
                val bundle = Bundle().apply {
                    putString("tareaId", tarea.id)
                }
                findNavController().navigate(R.id.action_pendientesFragment_to_tareasFragment, bundle)
            },
            onEliminar = { tarea ->
                viewModel.eliminarTarea(tarea.id)
            }
        )

        binding.recyclerTareas.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tareaAdapter
        }

        viewModel.tareas.observe(viewLifecycleOwner) { lista ->
            tareaAdapter.actualizarLista(lista)
        }

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_pendientesFragment_to_tareasFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



