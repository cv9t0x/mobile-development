enum class Direction {
    UP, RIGHT, DOWN, LEFT
}

class Robot(private val cords: Cords, private var direction: Direction) {
    fun getCords(): Cords {
        return cords
    }

    fun getDirection(): Direction {
        return direction
    }

    private fun turnRight() {
        direction = when (direction) {
            Direction.UP -> Direction.RIGHT
            Direction.RIGHT -> Direction.DOWN
            Direction.DOWN -> Direction.LEFT
            Direction.LEFT -> Direction.UP
        }
    }

    private fun turnLeft() {
        direction = when (direction) {
            Direction.UP -> Direction.LEFT
            Direction.LEFT -> Direction.DOWN
            Direction.DOWN -> Direction.RIGHT
            Direction.RIGHT -> Direction.UP
        }
    }

    private fun stepForward() {
        when (direction) {
            Direction.UP -> cords.incrementY()
            Direction.RIGHT -> cords.incrementX()
            Direction.DOWN -> cords.decrementY()
            Direction.LEFT -> cords.decrementX()
        }
    }

    fun move(toCords: Cords, withLog: Boolean = false) {
        var toDirection = if (toCords.getX() > cords.getX()) Direction.RIGHT else Direction.LEFT

        while (toDirection != direction) {
            turnRight()
            if (withLog) println(this)
        }

        while (toCords.getX() != cords.getX()) {
            stepForward()
            if (withLog) println(this)
        }

        toDirection = if (toCords.getY() > cords.getY()) Direction.UP else Direction.DOWN

        while(toDirection != direction) {
            turnLeft()
            if (withLog) println(this)
        }

        while (toCords.getY() != cords.getY()) {
            stepForward()
            if (withLog) println(this)
        }

        println("We have successfully arrived at the specified coordinates")
    }

    override fun toString(): String {
        return "cords: (${cords.getX()}, ${cords.getY()}), direction: $direction"
    }
}