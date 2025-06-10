package com.example.listadotareas.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import com. google. firebase. auth. FirebaseAuthInvalidUserException
import com. google. firebase. auth. FirebaseAuthInvalidCredentialsException

class SignInViewModel: ViewModel(){

    private val firebase = FirebaseAuth.getInstance()

    private val _loaderState = MutableLiveData<Boolean>()
    val loaderState: LiveData<Boolean> get() = _loaderState

    private val _sessionValid = MutableLiveData<Boolean>()
    val sessionValod: LiveData<Boolean> get() = _sessionValid

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun requestSingIn(email: String, password: String) {
        _loaderState.value = true

        viewModelScope.launch {
            try {
                val result = firebase.signInWithEmailAndPassword(email, password).await()
                _loaderState.value = false

                result.user?.let {
                    _sessionValid.value = true
                } ?: run {
                    Log.i("Firebase", "Usuario invalido de login")
                    _sessionValid.value = false
                    _errorMessage.value = "Ocurrio un error intente de nuevo."
                }

            } catch (e: Exception) {
                _loaderState.value = false
                _sessionValid.value = false

                Log.e("FirebaseLogin", "Error al logear", e)

                _errorMessage.value = when (e) {
                    is FirebaseAuthInvalidUserException -> "Este usuario no está registrado."
                    is FirebaseAuthInvalidCredentialsException -> "La contraseña es incorrecta."
                    else -> "Error: ${e.localizedMessage}"
                }
            }
        }
    }

}