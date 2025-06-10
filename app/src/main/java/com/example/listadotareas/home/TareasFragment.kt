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

class TareasFragment : Fragment() {

    private var _binding: FragmentTareasBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TareasViewModel by activityViewModels()

    var isValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTareasBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.btnRegreso2.setOnClickListener {
            findNavController().navigate(R.id.action_tareasFragment_to_pendientesFragment)
        }

        binding.Ittarean.addTextChangedListener {
            if (binding.Ittarean.text.toString().isEmpty()) {
                isValid = false
                binding.Itarean.error = "Ingrese nombre"
            } else {
                isValid = true
            }
        }

        binding.Ittaread.addTextChangedListener {
            if (binding.Ittaread.text.toString().isEmpty()) {
                isValid = false
                binding.Itaread.error = "Ingrese descripción"
            } else {
                isValid = true
            }
        }

        binding.Ittareaf.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                val fechaSeleccionada = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                binding.Ittareaf.setText(fechaSeleccionada)
            }, year, month, day)

            datePicker.show()
        }

        binding.button.setOnClickListener {
            val nombre = binding.Ittarean.text.toString().trim()
            val descripcion = binding.Ittaread.text.toString().trim()
            val fecha = binding.Ittareaf.text.toString().trim()

            if (nombre.isEmpty() || descripcion.isEmpty() || fecha.isEmpty()) {
                Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevaTarea = Tarea(nombre, descripcion, fecha)
            viewModel.agregarTarea(nuevaTarea)

            findNavController().navigate(R.id.action_tareasFragment_to_pendientesFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
