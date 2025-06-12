package com.example.listadotareas.home

import android.os.Bundle
import android.view.View

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.listadotareas.R
import com.example.listadotareas.databinding.ActivityListBinding
import com.example.listadotareas.utils.FragmentComunicator


class ListActivity : FragmentComunicator {


    private lateinit var binding: ActivityListBinding



    override fun showLoader(value: Boolean) {
        binding.loaderContainerView.visibility = if (value) View.VISIBLE else View.GONE
    }
    }
