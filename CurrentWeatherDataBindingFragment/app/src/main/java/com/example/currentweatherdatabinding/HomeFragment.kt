package com.example.currentweatherdatabinding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.currentweatherdatabinding.Weather.WeatherForecast
import com.example.currentweatherdatabinding.WeatherAdapter.WeatherAdapter
import com.example.currentweatherdatabinding.databinding.FragmentHomeBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var weatherForecastList = mutableListOf<WeatherForecast>()
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherAdapter = WeatherAdapter(LayoutInflater.from(binding.root.context)) {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToWeatherFragment(it)
            )
        }
        binding.weatherRv.adapter = weatherAdapter
        binding.submitBtn.setOnClickListener {
            val searchQuery = binding.searchEt.text.toString()
            if (searchQuery.length <= 3) {
                Toast.makeText(binding.root.context, "Min length - 3", Toast.LENGTH_SHORT).show()
            } else {
                GlobalScope.launch(Dispatchers.IO) {
                    loadWeather(searchQuery)
                }
                binding.searchEt.setText("")
            }
        }
        weatherAdapter.submitList(weatherForecastList)
    }

    private suspend fun loadWeather(city: String?) {
        val API_KEY = getString(R.string.API_KEY)
        val API_URL =
            "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$API_KEY&units=metric"

        try {
            val stream = withContext(Dispatchers.IO) {
                URL(API_URL).content
            } as InputStream
            val data = Scanner(stream).nextLine()
            val weatherForecast = Gson().fromJson(data, WeatherForecast::class.java)
            weatherForecastList.add(weatherForecast)
            withContext(Dispatchers.Main) {
                weatherAdapter.submitList(null)
                weatherAdapter.submitList(weatherForecastList)
            }
        } catch (e: java.lang.Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(binding.root.context, "Api Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}