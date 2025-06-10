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


class TareasFragment : Fragment() {

    private var _binding: FragmentTareasBinding? = null
    private val binding get() = _binding!!
    var isValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTareasBinding.inflate(inflater, container, false)
        setupView()
        return binding.root

    }


    private fun setupView(){
        binding.btnRegreso2.setOnClickListener {
            findNavController().navigate(R.id.action_tareasFragment_to_pendientesFragment)
        }

        binding.Ittarean.addTextChangedListener{
            if (binding.Ittarean.text.toString().isEmpty()){
                isValid = false
                binding.Itarean.error ="Ingrese nombre"
            }else{
                isValid = true
            }
        }

        binding.Ittaread.addTextChangedListener{
            if (binding.Ittaread.text.toString().isEmpty()){
                isValid = false
                binding.Itaread.error ="Ingrese descripcion"
            }else{
                isValid = true
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}