package com.example.seabattle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs),
    SurfaceHolder.Callback {

    private val boardSize = 10
    private val cellSize = 80
    private val ships = listOf(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)

    private var board: Array<IntArray> = Array(boardSize) { IntArray(boardSize) }

    private var isCreated = false

    init {
        holder.addCallback(this)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        drawBoardCanvas(canvas)
    }

    private fun drawBoardCanvas(canvas: Canvas) {
        val paint = Paint()
        canvas.drawColor(Color.WHITE)

        val boardWidth = cellSize * boardSize
        val startX = (canvas.width - boardWidth) / 2
        val startY = 0

        for (row in 0 until boardSize) {
            for (col in 0 until boardSize) {
                paint.color = Color.BLACK
                paint.style = Paint.Style.STROKE

                val left = startX + col * cellSize
                val top = startY + row * cellSize
                val right = left + cellSize
                val bottom = top + cellSize

                if (board[row][col] == 1) {
                    paint.style = Paint.Style.FILL
                }

                canvas.drawRect(
                    left.toFloat(),
                    top.toFloat(),
                    right.toFloat(),
                    bottom.toFloat(),
                    paint
                )
            }
        }
    }

    private fun generateBoard() {
        for (shipSize in ships) {
            var shipPlaced = false
            while (!shipPlaced) {
                val row = (0 until boardSize).random()
                val col = (0 until boardSize).random()
                val orientation = (0..1).random() // 0 - horizontal, 1 - vertical

                if (checkPlacement(row, col, shipSize, orientation)) {
                    placeShip(row, col, shipSize, orientation)
                    shipPlaced = true
                }
            }
        }
    }

    private fun checkPlacement(row: Int, col: Int, size: Int, orientation: Int): Boolean {
        if (orientation == 0) {
            if (col + size > boardSize) return false
            for (c in col until col + size) {
                if (board[row][c] == 1 || hasAdjacentCells(row, c)) {
                    return false
                }
            }
        } else {
            if (row + size > boardSize) return false
            for (r in row until row + size) {
                if (board[r][col] == 1 || hasAdjacentCells(r, col)) {
                    return false
                }
            }
        }
        return true
    }

    private fun hasAdjacentCells(row: Int, col: Int): Boolean {
        for (i in -1..1) {
            for (j in -1..1) {
                if (row + i in 0 until boardSize && col + j >= 0 && col + j < boardSize) {
                    if (board[row + i][col + j] == 1) {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun placeShip(row: Int, col: Int, size: Int, orientation: Int) {
        if (orientation == 0) {
            for (c in col until col + size) {
                board[row][c] = 1
            }
        } else {
            for (r in row until row + size) {
                board[r][col] = 1
            }
        }
    }

    private fun clearBoard() {
        board = Array(boardSize) { IntArray(boardSize) }
    }

    private fun drawBoard() {
        val canvas = holder.lockCanvas()
        draw(canvas)
        holder.unlockCanvasAndPost(canvas)
    }

    fun createBoard() {
        if (isCreated) {
            clearBoard()
        }
        generateBoard()
        drawBoard()
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        createBoard()
        isCreated = true
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
    }
}