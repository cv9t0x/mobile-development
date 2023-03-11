package com.example.currentweatherdatabinding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currentweatherdatabinding.Weather.WeatherForecast
import com.example.currentweatherdatabinding.WeatherAdapter.WeatherAdapter
import com.example.currentweatherdatabinding.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.io.InputStream
import java.net.URL
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var weatherForecastList =  mutableListOf<WeatherForecast>()
    private lateinit var weatherAdapter: WeatherAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherAdapter = WeatherAdapter(LayoutInflater.from(this))

        binding.weatherRv.adapter = weatherAdapter
        binding.submitBtn.setOnClickListener {
            val searchQuery = binding.searchEt.text.toString()
            if (searchQuery.length <= 3) {
                Toast.makeText(this, "Min length - 3", Toast.LENGTH_SHORT).show()
            } else {
                GlobalScope.launch(Dispatchers.IO) {
                    loadWeather(searchQuery)
                }
                binding.searchEt.setText("")
            }
        }
    }

    private suspend fun loadWeather(city: String?) {
        val API_KEY = getString(R.string.API_KEY)
        val API_URL = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$API_KEY&units=metric"

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
                Toast.makeText(applicationContext, "Api Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

}