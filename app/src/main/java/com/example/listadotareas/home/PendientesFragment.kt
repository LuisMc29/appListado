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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPendientesBinding.inflate(inflater, container, false)

        setupView()

        val recycler = binding.recyclerTareas
        recycler.layoutManager = LinearLayoutManager(context)

        viewModel.tareas.observe(viewLifecycleOwner) { lista ->
            recycler.adapter = TareaAdapter(lista)
        }

        return binding.root
    }

    private fun setupView() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_pendientesFragment_to_tareasFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
