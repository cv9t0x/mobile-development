package com.example.lamps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private lateinit var godObject: GameSurfaceView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        godObject = findViewById(R.id.surfaceView)

        godObject.isGameOver.observe(this) { isGameOver ->
            if (isGameOver) {
                val intent = Intent(this, WinActivity::class.java)
                startActivity(intent)
            }
        }
    }
}