package com.example.musicplayer

import MediaPlayerSingleton
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity(), MediaPlayerObserver {

    private lateinit var mediaPlayer: MediaPlayerSingleton
    private lateinit var playPauseButton: Button
    private lateinit var progressBarActivityButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MediaPlayerSingleton.addObserver(this)

        mediaPlayer = MediaPlayerSingleton.getInstance(this)
        playPauseButton = findViewById(R.id.playPauseButton)
        progressBarActivityButton = findViewById(R.id.progressBarActivityButton)

        initView()
    }

    private fun initView() {
        playPauseButton.text = if (mediaPlayer.isPlaying()) "Pause" else "Play"
        playPauseButton.setOnClickListener {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause()
                playPauseButton.text = "Play"
            } else {
                mediaPlayer.play()
                playPauseButton.text = "Pause"
            }
        }

        progressBarActivityButton.setOnClickListener {
            val intent = Intent(this, ProgressBarActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPlaybackComplete() {
        playPauseButton.text = "Play"
    }

    override fun onDestroy() {
        super.onDestroy()
        MediaPlayerSingleton.removeObserver(this)
    }
}