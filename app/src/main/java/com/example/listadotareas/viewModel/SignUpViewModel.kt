package com.example.listadotareas.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignUpViewModel: ViewModel() {
    private val _loaderState = MutableLiveData<Boolean>()
    val loaderState: LiveData<Boolean>
        get() = _loaderState
    private val firebase = FirebaseAuth.getInstance()

    fun requestSingUp(email:String, password:String){
        _loaderState.value = true
        viewModelScope.launch {
            val result = firebase.createUserWithEmailAndPassword(email, password).await()
            _loaderState.value = false
            result.user?.let {
                Log.i("Firebase", "Se creo el usuario exitosamente")
            }?:run{
                Log.i("Firebase", "Sucedio un problema")
            }
        }
    }

}