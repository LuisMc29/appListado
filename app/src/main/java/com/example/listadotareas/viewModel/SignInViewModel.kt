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

class SignInViewModel: ViewModel(){
    private val _loaderState = MutableLiveData<Boolean>()
    val loaderState: LiveData<Boolean>
        get() = _loaderState
    private val _sessionValid = MutableLiveData<Boolean>()
    val sessionValod: LiveData<Boolean>
        get() = _sessionValid
    private val firebase = FirebaseAuth.getInstance()

    fun requestSingIn(email:String, password:String){
        _loaderState.value = true
        viewModelScope.launch{
            val result =firebase.signInWithEmailAndPassword(email, password).await()
            _loaderState.value = false
            result.user?.let {
                _sessionValid.value = true
            } ?:run{
                Log.i("Firebase", "sucedio un problema")
            }
        }
    }

}