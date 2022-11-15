import kotlin.math.pow

class Square(var x: Int, var y: Int, var width: Int) : Transforming, Movable, Figure(2) {
    override fun resize(zoom: Int) {
        width *= zoom
    }

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        if (centerX == x && centerY == y) return
        if (direction == RotateDirection.Clockwise) x = y - centerY + centerX.also { y = -1 * (x - centerX) + centerY }
        if (direction == RotateDirection.CounterClockwise) x =
            -1 * (y - centerY) + centerX.also { y = x - centerX + centerY }
    }

    override fun move(dx: Int, dy: Int) {
        x += dx; y += dy
    }

    override fun area(): Float {
        return width.toDouble().pow(2).toFloat()
    }
}