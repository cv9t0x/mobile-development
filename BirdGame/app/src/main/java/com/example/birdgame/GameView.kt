package com.example.birdgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.util.Random
import java.util.Timer
import kotlin.concurrent.schedule


class GameView(context: Context, attributeSet: AttributeSet?) : SurfaceView(context, attributeSet),
    SurfaceHolder.Callback {
    private var gameThread: GameThread? = null

    private var player = Bird(context, 100f, 500f)
    private var walls = mutableListOf<Wall>()
    private var score = 0

    private var minY = 50f + player.getSize()

    private val random = Random()
    private var wallTimer: Timer? = null
    private var maxWalls = 3

    init {
        holder.addCallback(this)
        gameThread = GameThread(holder, this)
        isFocusable = true
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        gameThread?.start()
        startWallTimer()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                gameThread?.join()
                retry = false
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val newY = event.y.coerceIn(minY, height - minY)
                player.startFlapAnimation(newY)
            }
        }
        return true
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawColor(Color.WHITE)
        drawScore(canvas)

        player.draw(canvas)
        walls.forEach { it.draw(canvas) }
    }

    fun update() {
        updateWalls()

        Log.d("WallCount", walls.size.toString())
    }

    private fun startWallTimer() {
        wallTimer = Timer()
        val delay = random.nextInt(500) + 500

        wallTimer?.schedule(delay.toLong()) {
            if (walls.size < maxWalls) {
                createWall()
            }
            startWallTimer()
        }
    }

    private fun createWall() {
        val screenHeight = height
        val minHeight = screenHeight / 4
        val maxHeight = screenHeight / 2
        val wallHeight = random.nextInt(maxHeight - minHeight) + minHeight.toFloat()

        val wallWidth = 100f

        val isWallAtTop = random.nextBoolean()
        val wallY = if (isWallAtTop) 0f else screenHeight - wallHeight

        val wallX = width.toFloat() - wallWidth

        val wall = Wall(context, wallWidth, wallHeight, wallX, wallY)
        walls.add(wall)
    }

    private fun removeWall(wall: Wall) {
        walls.remove(wall)
    }

    private fun updateWalls() {
        val iterator = walls.iterator()
        while (iterator.hasNext()) {
            val wall = iterator.next()
            if (wall.getX() + wall.getWidth() < 0) {
                iterator.remove()
                removeWall(wall)
            } else {
                wall.update()
            }
        }
    }

    private fun drawScore(canvas: Canvas) {
        val paint = Paint().apply {
            color = Color.BLACK
            textSize = 100f
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("Score: $score", width / 2f, 150f, paint)
    }
}