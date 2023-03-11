package com.example.currentweatherdatabinding.WeatherAdapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currentweatherdatabinding.R

class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val cityTv: TextView = itemView.findViewById<TextView>(R.id.cityTv)
    val tempTv: TextView = itemView.findViewById<TextView>(R.id.tempTv)
    val iconIv: ImageView = itemView.findViewById<ImageView>(R.id.iconIv)
    val descTv: TextView = itemView.findViewById<TextView>(R.id.descTv)
}