package com.example.currentweatherdatabinding.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.currentweatherdatabinding.R
import com.example.currentweatherdatabinding.databinding.FragmentSimpleWeatherBinding


class SimpleWeatherFragment : Fragment() {
    private val args by navArgs<SimpleWeatherFragmentArgs>()

    private lateinit var binding: FragmentSimpleWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSimpleWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cityTv.text = args.weatherForecast.name
        binding.tempTv.text = args.weatherForecast.main.temp + "°C"
        binding.root.findViewById<Button>(R.id.backBtn)
            .setOnClickListener { v -> v.findNavController().popBackStack() }
    }
}