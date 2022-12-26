package com.example.colortiles

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlin.random.Random

class ColorTiles(context: Context?) : View(context) {
    private val N = 4
    private val tiles = Array(N) { BooleanArray(N) { Random.nextBoolean() } }
    private val radius = 100f;
    private val gap = 40f;
    private var won = false;

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        val p = Paint();

        p.color = Color.RED
        canvas?.apply {
            drawColor(Color.parseColor("#FFFFFF"))

            var cnt = 0

            for (i in 0 until N) {
                for (j in 0 until N) {
                    p.color = if (tiles[i][j]) Color.parseColor("#0000FF") else Color.parseColor("#000000")

                    cnt += if (tiles[i][j]) 1 else 0;

                    drawCircle(
                        j * radius * 2f + (j + 1f) * gap + radius,
                        i * radius * 2f + (i + 1f) * gap + radius,
                        radius,
                        p
                    )
                }
            }

            won = cnt == 0 || cnt == N * N;

            if (won) {
                val text = "CONGRATULATIONS!!!"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(context, text, duration)
                toast.show()
            }
        }
    }

    private fun distanceSquared(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return (x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (won) return false;

        event?.apply {
            if (action != MotionEvent.ACTION_DOWN) return false;
            var touchedX: Int? = null;
            var touchedY: Int? = null;

            for (i in 0 until N) {
                for (j in 0 until N) {
                    val cx = j * radius * 2f + (j + 1f) * gap + radius
                    val cy = i * radius * 2f + (i + 1f) * gap + radius
                    val dist = distanceSquared(cx, cy, x, y)

                    if (dist <= radius * radius) {
                        touchedY = i
                        touchedX = j
                        break
                    }
                }
            }

            if (touchedX != null && touchedY != null) {
                invertColors(touchedY, touchedX)
            }
            invalidate()
        }
        return true
    }

    private fun invertColors(y: Int, x: Int) {
        for (i in 0 until N) {
            tiles[y][i] = !tiles[y][i]
            tiles[i][x] = !tiles[i][x]
        }

        tiles[y][x] = !tiles[y][x]
    }
}