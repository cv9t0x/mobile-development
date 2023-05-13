package com.example.lamps

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class GameSurfaceView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs), SurfaceHolder.Callback {

    private val circles = mutableListOf<Circle>()
    private val squareSize = 4
    private val _isGameOver = MutableLiveData(false)

    val isGameOver: LiveData<Boolean>
        get() = _isGameOver

    init {
        holder.addCallback(this)
    }

    private fun setupCircles(startX: Float, startY: Float) {
        val diameter = 150f
        val radius = diameter / 2
        val margin = 15f
        val centerX = startX - squareSize / 2 * diameter

        for (i in 0 until squareSize) {
            for (j in 0 until squareSize) {
                val cx = centerX + (diameter + margin) * i
                val cy = startY + (diameter + margin) * j
                val isFilled = Random.nextBoolean()
                val color = Color.BLACK
                val circle = Circle(cx, cy, radius, isFilled, color)
                circles.add(circle)
            }
        }
    }

    private fun drawCircles() {
        val canvas = holder.lockCanvas()
        canvas.drawColor(Color.WHITE)

        circles.forEach { circle ->
            val paint = Paint().apply {
                style = if (circle.isFilled) Paint.Style.FILL else Paint.Style.STROKE
                strokeWidth = 5f
                color = circle.color
            }
            canvas.drawCircle(circle.cx, circle.cy, circle.radius, paint)
        }

        holder.unlockCanvasAndPost(canvas)
    }

    private fun checkWin(isFilled: Boolean) {
        var flag = true
        for (circle in circles) {
            if (circle.isFilled != isFilled) {
                flag = false
            }
        }
        _isGameOver.value = flag
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val x = event.x
                val y = event.y

                var selectedCircleIndex = -1
                for (i in circles.indices) {
                    val circle = circles[i]
                    if (x > circle.cx - circle.radius && x < circle.cx + circle.radius &&
                        y > circle.cy - circle.radius && y < circle.cy + circle.radius) {
                        selectedCircleIndex = i
                        break
                    }
                }

                if (selectedCircleIndex != -1) {
                    val selectedCircle = circles[selectedCircleIndex]
                    for (i in circles.indices) {
                        val circle = circles[i]
                        if (circle.cx == selectedCircle.cx || circle.cy == selectedCircle.cy) {
                            circle.isFilled = !circle.isFilled
                        }
                    }
                    drawCircles()
                    checkWin(selectedCircle.isFilled)
                }
            }
        }
        return true
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        setupCircles(width / 2f, width / 2f)
        drawCircles()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
    }

    inner class Circle(val cx: Float, val cy: Float, val radius: Float, var isFilled: Boolean, val color: Int)
}
