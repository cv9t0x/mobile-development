import kotlin.math.pow
import kotlin.math.sqrt

class Circle(var x: Int, var y: Int, var r: Int) : Transforming, Movable, Figure(1) {
    override fun resize(zoom: Int) {
        r *= zoom
    }

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        if (centerX == x && centerY == y) return
        if (direction == RotateDirection.Clockwise) x = y - centerY + centerX.also { y = -1 * (x - centerX) + centerY }
        if (direction == RotateDirection.CounterClockwise) x =
            -1 * (y - centerY) + centerX.also { y = x - centerX + centerY }
    }

    override fun area(): Float {
        return (Math.PI * r.toDouble().pow(2)).toFloat();
    }

    override fun move(dx: Int, dy: Int) {
        x += dx; y += dy
    }
}