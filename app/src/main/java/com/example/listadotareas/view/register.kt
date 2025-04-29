package com.example.listadotareas.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.listadotareas.R
import com.example.listadotareas.databinding.FragmentRegisterBinding
import com.example.listadotareas.utils.FragmentComunicator
import com.example.listadotareas.viewModel.SignUpViewModel
import androidx.core.widget.addTextChangedListener

class register : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SignUpViewModel>()
    private lateinit var communicator: FragmentComunicator
    var isValid: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        communicator = requireActivity() as MainActivity
        setupView()
        return binding.root
    }


    private fun setupView() {
        binding.imageButton2.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_login)
        }

        binding.btnRegister.setOnClickListener{
            viewModel.requestSingUp(binding.Itcorreo2.text.toString(),
                    binding.Itcontrasenia2.text.toString())
        }

        binding.Itnombre.addTextChangedListener{
            if(binding.Itnombre.text.toString().isEmpty()){
                binding.Inombre.error = "Por favor ingrese un nombre"
                isValid = false
            }else{
                isValid = true
            }
        }

        binding.Itcorreo2.addTextChangedListener{
            if(binding.Itcorreo2.text.toString().isEmpty()){
                binding.Icorreo2.error = "Ingrese un correo valido"
                isValid = false
            }else{
                isValid = true
            }
        }

        binding.Itcontrasenia2.addTextChangedListener{
            if(binding.Itcontrasenia2.text.toString().isEmpty()){
                binding.Icontrasenia2.error = "Ingrese una contraseña valida"
                isValid = false
            }else{
                isValid = true
            }
        }
        setupObservers()
    }

    private fun setupObservers(){
    viewModel.loaderState.observe(viewLifecycleOwner){ loaderState ->
        communicator.showLoader(loaderState)
    }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}