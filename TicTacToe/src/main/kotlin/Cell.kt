enum class Value() {
    X, O
}


class Cell {
    private var value: Value? = null

    fun setValue(value: Value) {
        this.value = value
    }

    fun restart() {
        this.value = null
    }

    fun getValue(): Value? {
        return this.value
    }

    fun isEmpty(): Boolean {
        return this.value == null
    }


    override fun toString(): String {
        return if (this.value == null) "-" else this.value.toString()
    }
}