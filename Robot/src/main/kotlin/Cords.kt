class Cords(private var x: Int, private var y: Int) {
    init {
        x = if (x >= 0) x else 0
        y = if (y >= 0) y else 0
    }

    fun getX(): Int {
        return x
    }

    fun getY(): Int {
        return y
    }

    fun incrementX() {
        x += 1
    }

    fun incrementY() {
        y += 1
    }

    fun decrementX() {
        x -= 1
    }

    fun decrementY() {
        y -= 1
    }
}