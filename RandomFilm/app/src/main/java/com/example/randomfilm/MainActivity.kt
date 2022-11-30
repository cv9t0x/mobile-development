package com.example.randomfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var movies : ArrayList<String>
    private val r = Random()

    private fun init() {
        val titleView = findViewById<TextView>(R.id.title)
        titleView.text = "Нажмите на кнопку \'Следуюший фильм\'"
        movies = resources.getStringArray(R.array.movies).toCollection(ArrayList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init();
    }

    fun onNext(view: View) {
        val titleView = findViewById<TextView>(R.id.title)

        if (movies.isEmpty()) {
            titleView.text = "Фильмы закончились, нажмите на кнопку \'Сбросить\'"
            return
        }

        val idx = r.nextInt(movies.size)
        titleView.text = movies[idx]
        movies.removeAt(idx)
    }

    fun onReset(view: View) {
        init();
    }
 }