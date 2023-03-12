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
import com.example.currentweatherdatabinding.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {
    private val args by navArgs<WeatherFragmentArgs>()

    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cityTv.text = args.weatherForecast.name
        binding.tempTv.text = args.weatherForecast.main.temp + "°C"
        binding.feelsLikeTv.text = args.weatherForecast.main.feels_like + "°C"
        binding.humidityTv.text = args.weatherForecast.main.humidity + "%"
        binding.windSpeedTv.text = args.weatherForecast.wind.speed + "%"
        binding.root.findViewById<Button>(R.id.backBtn)
            .setOnClickListener { v -> v.findNavController().popBackStack() }
    }

}