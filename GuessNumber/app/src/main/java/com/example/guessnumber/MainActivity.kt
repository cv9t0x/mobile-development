package com.example.guessnumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var startRangeEditText: EditText
    lateinit var endRangeEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startRangeEditText = findViewById(R.id.start_range)
        endRangeEditText = findViewById(R.id.end_range)
    }

    fun onStart(view: View) {
        val startRange = startRangeEditText.text.toString().toInt()
        val endRange = endRangeEditText.text.toString().toInt()

        if (startRange >= endRange) {
            Toast.makeText(this, "Начала диапозона должно быть меньше чем конец", Toast.LENGTH_LONG).show()
            return
        }

        if (endRange == startRange + 1) {
            Toast.makeText(this, "Разница между началом и концом должно быть как минимум 1", Toast.LENGTH_LONG).show()
            return
        }

        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("startRange", startRange)
        intent.putExtra("endRange", endRange)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        startRangeEditText.setText("")
        endRangeEditText.setText("")
    }
}