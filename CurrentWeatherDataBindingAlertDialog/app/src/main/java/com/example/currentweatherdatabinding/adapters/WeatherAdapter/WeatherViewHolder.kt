package com.example.currentweatherdatabinding.adapters.WeatherAdapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currentweatherdatabinding.R
import com.example.currentweatherdatabinding.models.WeatherForecast
import com.squareup.picasso.Picasso

class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val cityTv: TextView = itemView.findViewById(R.id.cityTv)

    private val tempTv: TextView = itemView.findViewById(R.id.tempTv)

    private val iconIv: ImageView = itemView.findViewById(R.id.iconIv)

    private val descTv: TextView = itemView.findViewById(R.id.descTv)

    fun bind(model: WeatherForecast) {
        cityTv.text = model.name
        tempTv.text = model.main.temp + "°C"
        Picasso.get().load("https://openweathermap.org/img/w/${model.weather[0].icon}.png")
            .into(iconIv)
        descTv.text = model.weather[0].description
    }
}