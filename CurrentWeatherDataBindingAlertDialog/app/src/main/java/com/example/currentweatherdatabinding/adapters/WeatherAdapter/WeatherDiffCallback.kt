package com.example.currentweatherdatabinding.adapters.WeatherAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.currentweatherdatabinding.models.WeatherForecast

object WeatherDiffCallback : DiffUtil.ItemCallback<WeatherForecast>() {
    override fun areItemsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}