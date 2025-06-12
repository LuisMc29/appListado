package com.example.listadotareas.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.listadotareas.R
import com.example.listadotareas.databinding.FragmentPendientesBinding
import com.example.listadotareas.databinding.FragmentTareasBinding
import java.util.Calendar
import android. app. DatePickerDialog
import android. widget. Toast
import com. example. listadotareas. viewModel. TareasViewModel
import androidx. fragment. app. activityViewModels
import com. example. listadotareas. model. Tarea

/*
class TareasFragment : Fragment() {

    private var _binding: FragmentTareasBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TareasViewModel by activityViewModels()

    private var tareaEditando: Tarea? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTareasBinding.inflate(inflater, container, false)

        val tareaId = arguments?.getString("tareaId")

        tareaId?.let { id ->
            tareaEditando = viewModel.obtenerTareaPorId(id)
            tareaEditando?.let {
                binding.Ittarean.setText(it.nombre)
                binding.Ittaread.setText(it.descripcion)
                binding.Ittareaf.setText(it.fecha)
            }
        }

        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.btnRegreso2.setOnClickListener {
            findNavController().navigate(R.id.action_tareasFragment_to_pendientesFragment)
        }

        binding.Ittareaf.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, y, m, d ->
                    binding.Ittareaf.setText(String.format("%02d/%02d/%04d", d, m + 1, y))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.button.setOnClickListener {
            val nombre = binding.Ittarean.text.toString().trim()
            val descripcion = binding.Ittaread.text.toString().trim()
            val fecha = binding.Ittareaf.text.toString().trim()

            if (nombre.isEmpty() || descripcion.isEmpty() || fecha.isEmpty()) {
                Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (tareaEditando != null) {
                val tareaActualizada = tareaEditando!!.copy(
                    nombre = nombre,
                    descripcion = descripcion,
                    fecha = fecha
                )
                viewModel.actualizarTarea(tareaActualizada)
            } else {
                val nuevaTarea = Tarea(
                    nombre = nombre , descripcion = descripcion , fecha=fecha
                )
                viewModel.agregarTarea(nuevaTarea)
            }

            findNavController().navigate(R.id.action_tareasFragment_to_pendientesFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
 */

class TareasFragment : Fragment() {

    private var _binding: FragmentTareasBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TareasViewModel by activityViewModels()

    private var tareaEditando: Tarea? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTareasBinding.inflate(inflater, container, false)

        val tareaId = arguments?.getString("tareaId")

        tareaId?.let { id ->
            viewModel.obtenerTareaPorId(id)?.let {
                tareaEditando = it
                binding.Ittarean.setText(it.nombre)
                binding.Ittaread.setText(it.descripcion)
                binding.Ittareaf.setText(it.fecha)
            }
        }

        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.btnRegreso2.setOnClickListener {
            findNavController().navigate(R.id.action_tareasFragment_to_pendientesFragment)
        }

        binding.Ittareaf.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                { _, y, m, d ->
                    binding.Ittareaf.setText(String.format("%02d/%02d/%04d", d, m + 1, y))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.button.setOnClickListener {
            val nombre = binding.Ittarean.text.toString().trim()
            val descripcion = binding.Ittaread.text.toString().trim()
            val fecha = binding.Ittareaf.text.toString().trim()

            if (nombre.isEmpty() || descripcion.isEmpty() || fecha.isEmpty()) {
                Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (tareaEditando != null) {
                val tareaActualizada = tareaEditando!!.copy(
                    nombre = nombre,
                    descripcion = descripcion,
                    fecha = fecha
                )
                viewModel.actualizarTarea(tareaActualizada)
            } else {
                val nuevaTarea = Tarea(
                    nombre = nombre,
                    descripcion = descripcion,
                    fecha = fecha
                )
                viewModel.agregarTarea(nuevaTarea)
            }

            findNavController().navigate(R.id.action_tareasFragment_to_pendientesFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

