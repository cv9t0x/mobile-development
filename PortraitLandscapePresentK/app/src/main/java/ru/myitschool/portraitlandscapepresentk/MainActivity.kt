package ru.myitschool.portraitlandscapepresentk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap

class MainActivity : AppCompatActivity() {
    private var idx = 0
    private lateinit var iv: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.iv = findViewById<ImageView>(R.id.picture)
        savedInstanceState?.let {
            this.idx = it.getInt("idx")
            when (this.idx) {
                0 -> return
                1 -> this.iv.setImageResource(R.drawable.car1)
                2 -> this.iv.setImageResource(R.drawable.car2)
                3 -> this.iv.setImageResource(R.drawable.car3)
            }
        }
    }

    fun onChangePictureClick(v: View) {
        this.idx += 1
        if (this.idx == 4) this.idx = 1
        when (this.idx) {
            1 -> this.iv.setImageResource(R.drawable.car1)
            2 -> this.iv.setImageResource(R.drawable.car2)
            3 -> {
                this.iv.setImageResource(R.drawable.car3)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("main", this.iv.drawable.toBitmap().toString())
        outState.putInt("idx", this.idx)
    }
}