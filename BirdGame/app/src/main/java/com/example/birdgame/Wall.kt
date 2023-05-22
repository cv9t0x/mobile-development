package com.example.birdgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Wall(
    private val context: Context,
    private val width: Float,
    private val height: Float,
    private var x: Float,
    private var y: Float,
) {
    private var speed = 10f

    private val rect = RectF()

    private val paint: Paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    fun getX(): Float {
        return x
    }

    fun getWidth(): Float {
        return width
    }

    fun draw(canvas: Canvas) {
        rect.set(x, y, x + width, y + height)
        canvas.drawRect(rect, paint)
    }

    fun update() {
        x -= speed
    }
}