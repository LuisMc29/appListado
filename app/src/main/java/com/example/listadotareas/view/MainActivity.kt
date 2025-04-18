package com.example.listadotareas.view

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listadotareas.R
import android.view.View
import com.example.listadotareas.databinding.ActivityMainBinding
import com.example.listadotareas.utils.FragmentComunicator

class MainActivity : AppCompatActivity(), FragmentComunicator {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        }

    override fun showLoader(value: Boolean){
        binding.loaderContainerView.visibility = if(value) View.VISIBLE else View.GONE
    }
}