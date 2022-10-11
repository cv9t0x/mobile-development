enum class Value {
    X, O
}


class Cell {
    private var value: Value? = null

    fun setValue(value: Value) {
        this.value = value
    }

    fun getValue(): Value? {
        return this.value
    }

    fun isEmpty(): Boolean {
        return this.value != null
    }

    private fun convertToString(): String {
        if (this.value == null) {
            return "-"
        }
        return if (this.value == Value.X) "x" else "o"
    }

    override fun toString(): String {
        return this.convertToString()
    }
}