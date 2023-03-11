package com.example.currentweatherdatabinding.WeatherAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.currentweatherdatabinding.R
import com.example.currentweatherdatabinding.Weather.WeatherForecast
import com.squareup.picasso.Picasso

class WeatherAdapter(private val inflater: LayoutInflater): ListAdapter<WeatherForecast, WeatherViewHolder>(WeatherDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view: View = inflater.inflate(R.layout.weather_card, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = getItem(position)
        holder.cityTv.text = item.name
        holder.tempTv.text = item.main.temp + "°C"
        Picasso.get().load("https://openweathermap.org/img/w/${item.weather[0].icon}.png").into(holder.iconIv)
        holder.descTv.text = item.weather[0].description
    }
}