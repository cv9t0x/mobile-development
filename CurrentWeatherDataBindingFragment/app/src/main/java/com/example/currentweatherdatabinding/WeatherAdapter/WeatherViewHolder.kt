package com.example.currentweatherdatabinding.WeatherAdapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currentweatherdatabinding.R
import com.example.currentweatherdatabinding.Weather.WeatherForecast
import com.squareup.picasso.Picasso

class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cityTv: TextView = itemView.findViewById<TextView>(R.id.cityTv)
    val tempTv: TextView = itemView.findViewById<TextView>(R.id.tempTv)
    val iconIv: ImageView = itemView.findViewById<ImageView>(R.id.iconIv)
    val descTv: TextView = itemView.findViewById<TextView>(R.id.descTv)

    fun bind(model: WeatherForecast) {
        cityTv.text = model.name
        tempTv.text = model.main.temp + "°C"
        Picasso.get().load("https://openweathermap.org/img/w/${model.weather[0].icon}.png")
            .into(iconIv)
        descTv.text = model.weather[0].description
    }
}