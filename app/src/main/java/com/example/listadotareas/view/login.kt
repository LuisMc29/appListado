package com.example.listadotareas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.listadotareas.R
import com.example.listadotareas.databinding.FragmentLoginBinding
import com.example.listadotareas.viewModel.SignInViewModel
import com.example.listadotareas.utils.FragmentComunicator

class login : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SignInViewModel>()
    private lateinit var communicator: FragmentComunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        communicator = requireActivity() as MainActivity
        setupView()
        setupObservers()
        return binding.root
    }

    private fun setupView() {
        binding.textView4.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }

        binding.btnLogin.setOnClickListener {
            if (validarCampos()) {
                requestLogin()
            } else {
                Toast.makeText(activity, "Por favor completa todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.Itcorreo.addTextChangedListener {
            if (binding.Itcorreo.text.toString().isEmpty()) {
                binding.Icorreo.error = "Por favor introduce un correo"
            } else {
                binding.Icorreo.error = null
            }
        }

        binding.Itcontrasenia.addTextChangedListener {
            if (binding.Itcontrasenia.text.toString().isEmpty()) {
                binding.Icontrasenia.error = "Por favor introduce una contraseña"
            } else {
                binding.Icontrasenia.error = null
            }
        }
    }

    private fun setupObservers() {
        viewModel.loaderState.observe(viewLifecycleOwner) { loaderState ->
            communicator.showLoader(loaderState)
        }

        viewModel.sessionValod.observe(viewLifecycleOwner) { validSesion ->
            if (validSesion) {
                findNavController().navigate(R.id.action_login_to_pendientesFragment)
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun requestLogin() {
        val email = binding.Itcorreo.text.toString()
        val password = binding.Itcontrasenia.text.toString()
        viewModel.requestSingIn(email, password)
    }

    private fun validarCampos(): Boolean {
        val correo = binding.Itcorreo.text.toString()
        val contrasenia = binding.Itcontrasenia.text.toString()
        var esValido = true

        if (correo.isEmpty()) {
            binding.Icorreo.error = "Por favor introduce un correo"
            esValido = false
        }

        if (contrasenia.isEmpty()) {
            binding.Icontrasenia.error = "Por favor introduce una contraseña"
            esValido = false
        }

        return esValido
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
