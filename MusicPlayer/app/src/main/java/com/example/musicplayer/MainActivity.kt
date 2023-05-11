package com.example.musicplayer

import MediaPlayerSingleton
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayerSingleton
    private lateinit var playPauseButton: Button
    private lateinit var progressBarActivityButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayerSingleton.getInstance(this)
        playPauseButton = findViewById(R.id.playPauseButton)
        progressBarActivityButton = findViewById(R.id.progressBarActivityButton)

        initView()
    }

    private fun initView() {
        mediaPlayer.isPlaying.observe(this, Observer { isPlaying ->
            playPauseButton.text = if (isPlaying) "Pause" else "Play"

        })

        playPauseButton.setOnClickListener {
            if (mediaPlayer.isPlaying.value == true) mediaPlayer.pause()
            else mediaPlayer.play()
        }

        progressBarActivityButton.setOnClickListener {
            val intent = Intent(this, ProgressBarActivity::class.java)
            startActivity(intent)
        }
    }
}