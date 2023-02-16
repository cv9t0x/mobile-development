package com.example.recyclerviewcolorsk

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), SelectListener {

    val planetsList = arrayListOf<String>("Mars", "Venus", "Earth")
    val palette = Utils.generatePalette(planetsList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // пример использования ListView
        val lv = findViewById<ListView>(R.id.list)
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, planetsList)
        lv.adapter = adapter

        // пример использования RecyclerView с собственным адаптером
        val rv = findViewById<RecyclerView>(R.id.rview)
        val colorAdapter = ColorAdapter(LayoutInflater.from(this), this)
        // добавляем данные в список для отображения
        colorAdapter.submitList(palette["Mars"])
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = colorAdapter

        lv.setOnItemClickListener { adapterView, _, i, _ ->
            val selectedItem = adapterView.getItemAtPosition(i) as String
            colorAdapter.submitList(palette[selectedItem])
            colorAdapter.notifyDataSetChanged()
        }
    }

    override fun onItemClicked(color: Int) {
        Toast.makeText(this, getString(R.string.template, color), Toast.LENGTH_SHORT).show()
    }
}
