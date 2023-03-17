package com.example.tabsdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tabsdemo.databinding.FragmentWeatherBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL
import java.util.*
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val ARG_CITY = "cityParam"

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    private var city: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            city = it.getString(ARG_CITY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        GlobalScope.launch(Dispatchers.IO) {
            loadWeather(city)
        }
        return binding.root
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
            Log.d("myTag", data)
            val weatherForecast = Gson().fromJson(data, WeatherForecast::class.java)
            withContext(Dispatchers.Main) {
                binding.weatherForecast = weatherForecast
                Picasso.get().load("https://openweathermap.org/img/w/${weatherForecast.weather[0].icon}.png").placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(binding.iconIv)
            }
        } catch (e: java.lang.Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(binding.root.context, "API ERROR", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(cityArg: String) =
            WeatherFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CITY, cityArg)
                }
            }
    }
}