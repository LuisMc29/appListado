package com.example.listadotareas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.listadotareas.R
import com.example.listadotareas.databinding.FragmentLoginBinding
import com.example.listadotareas.viewModel.SignInViewModel
import com.example.listadotareas.utils.FragmentComunicator


class login : Fragment() {
    // TODO: Rename and change types of parameters

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SignInViewModel>()
    var isValid: Boolean = false
    private lateinit var communicator: FragmentComunicator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        setupView()
       /* setupObservers()
        */
        return binding.root
    }


    private fun setupView() {
        binding.textView4.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }
        binding.btnLogin.setOnClickListener {
            if (isValid) {
                /*
                requestLogin()

                 */
            } else {
                Toast.makeText(activity, "Ingreso invalido", Toast.LENGTH_SHORT).show()
            }
        }
        binding.Itcorreo.addTextChangedListener {
            if (binding.Itcorreo.text.toString().isEmpty()) {
                binding.Icorreo.error = "Por favor introduce un correo"
                isValid = false
            } else {
                isValid = true
            }
        }
        binding.Itcontrasenia.addTextChangedListener {
            if (binding.Itcontrasenia.text.toString().isEmpty()) {
                binding.Icontrasenia.error = "Por favor introduce una contraseña"
                isValid = false
            } else {
                isValid = true
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}