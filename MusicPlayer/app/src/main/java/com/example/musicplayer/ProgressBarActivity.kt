package com.example.musicplayer

import MediaPlayerSingleton
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProgressBarActivity : AppCompatActivity(), MediaPlayerObserver {

    private lateinit var mediaPlayer: MediaPlayerSingleton
    private lateinit var playPauseButton: Button
    private lateinit var mainActivityButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var timer: TextView
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar)

        MediaPlayerSingleton.addObserver(this)

        mediaPlayer = MediaPlayerSingleton.getInstance(this)
        playPauseButton = findViewById(R.id.playPauseButton)
        mainActivityButton = findViewById(R.id.mainActivityButton)
        progressBar = findViewById(R.id.progressBar)
        timer = findViewById(R.id.timer)
        handler = Handler(Looper.getMainLooper())

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

        mainActivityButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        progressBar.max = mediaPlayer.getDuration()
        handler.post(updateProgressRunnable)
    }

    private val updateProgressRunnable = object : Runnable {
        override fun run() {
            val position = mediaPlayer.getCurrentPosition()
            progressBar.progress = position
            timer.text = formatTime(position)
            handler.postDelayed(this, 1000)
        }
    }

    private fun formatTime(millis: Int): String {
        val totalSeconds = millis / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    override fun onPlaybackComplete() {
        playPauseButton.text = "Play"
    }

    override fun onDestroy() {
        super.onDestroy()
        MediaPlayerSingleton.removeObserver(this)
        handler.removeCallbacks(updateProgressRunnable)
    }
}