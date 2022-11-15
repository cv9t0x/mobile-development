interface Transforming {
    fun resize(zoom: Int)

    fun rotate(direction: RotateDirection, centerX: Int, centerY: Int)
}

enum class RotateDirection {
    Clockwise, CounterClockwise
}
