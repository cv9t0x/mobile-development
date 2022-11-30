package com.example.guessnumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity()  {
    lateinit var titleView: TextView
    lateinit var moreBtn: Button
    lateinit var lessBtn: Button
    lateinit var homeBtn: Button
    var startRange: Int = 0
    var endRange: Int = 0
    var average: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        titleView = findViewById(R.id.title)
        moreBtn = findViewById(R.id.more_btn)
        lessBtn = findViewById(R.id.less_btn)
        homeBtn = findViewById(R.id.home_btn)

        startRange = intent.getIntExtra("startRange", 0)
        endRange = intent.getIntExtra("endRange", 0)

        if (!checkFinish()) {
            setAverage()
            setTitle()
        }
    }

    private fun setAverage() {
        average = endRange - (endRange - startRange) / 2
    }

    private fun setTitle() {
        titleView.text = "Ваше число: ${average}?"
    }

    private fun checkFinish(): Boolean {
        if (endRange - startRange <= 2) {
            titleView.text = "Ваше число: ${endRange - 1}"
            moreBtn.visibility = View.GONE
            lessBtn.visibility = View.GONE
            homeBtn.visibility = View.VISIBLE
            return true
        }
        return false
    }

    private fun makeMove() {
        setAverage()
        setTitle()
        checkFinish()
    }

    fun onLess(view: View) {
        endRange = average
        makeMove()
    }

    fun onMore(view: View) {
        startRange = average
        makeMove()
    }

    fun onHome(view: View) {
        this.finish()
    }
}