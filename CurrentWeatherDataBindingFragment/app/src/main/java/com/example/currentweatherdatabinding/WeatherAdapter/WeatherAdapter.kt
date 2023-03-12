package com.example.currentweatherdatabinding.WeatherAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.currentweatherdatabinding.R
import com.example.currentweatherdatabinding.Weather.WeatherForecast
import com.squareup.picasso.Picasso

class WeatherAdapter(private val inflater: LayoutInflater, private val onItemClicked: (WeatherForecast) -> Unit) :
    ListAdapter<WeatherForecast, WeatherViewHolder>(WeatherDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view: View = inflater.inflate(R.layout.weather_card, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClicked(item) }
    }
}