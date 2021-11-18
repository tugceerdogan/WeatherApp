package com.example.weatherapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.viewmodel.FirstViewModel
import com.example.weatherapp.databinding.FirstFragmentBinding
import com.example.weatherapp.util.Resource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private lateinit var _binding: FirstFragmentBinding

    private var lat : Double = 0.0
    private var lon : Double = 0.0

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FirstFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)

        val viewModel: FirstViewModel by viewModels()

        _binding.btnEnter.setOnClickListener() {

            val key = _binding.editTextAPIkey.editText?.text.toString()

            getLastKnownLocation()

            viewModel.enter(lon,lat,"daily",key)
                .observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            val action =
                               FirstFragmentDirections.actionFirstFragmentToSecondFragment()
                            findNavController().navigate(action)

                        }
                        Resource.Status.ERROR -> {
                            Log.v("ERROR", "There is a data access error")

                        }
                    }
                })
        }
    }

    private fun getLastKnownLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location->
                if (location != null) {
                    lat= location.latitude
                    lon= location.longitude

                }

            }

    }
}