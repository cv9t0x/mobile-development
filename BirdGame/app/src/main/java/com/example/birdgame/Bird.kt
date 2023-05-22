package com.example.birdgame

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Bird(context: Context, private var x: Float,  private var y: Float) {

    private val width = 100f
    private val height = 100f

    private val rect = RectF()

    fun getSize(): Float {
        return width
    }

    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }

    fun draw(canvas: Canvas) {
        rect.set(x, y, x + width, y + height)
        canvas.drawRect(rect, paint)
    }

    fun startFlapAnimation(newY: Float) {
        val startValue = y
        val duration = 300L

        ValueAnimator.ofFloat(startValue, newY - height / 2).apply {
            this.duration = duration
            addUpdateListener { animator ->
                y = animator.animatedValue as Float
            }
            start()
        }
    }
}