import java.lang.Exception

class FieldException(message: String) : Exception(message)

class Field {
    private var x: Int
    private var y: Int
    private var size: Int
    private var cells: Array<Array<Cell>>

    constructor(x: Int, y: Int, size: Int) {
        if (size <= 1) throw FieldException("Size can't be less or equal to one")
        if (size % 2 == 0) throw FieldException("Size must be an odd number")
        this.x = x
        this.y = y
        this.size = size
        this.cells = Array<Array<Cell>>(this.size) { Array(this.size) { Cell() } }
    }

    fun getX(): Int {
        return this.x
    }

    fun getY(): Int {
        return this.y
    }

    fun getCells(): Array<Array<Cell>> {
        return this.cells
    }

    fun printCells() {
        this.cells.forEachIndexed { rowIndex, row ->
            row.forEach { cell ->
                print(cell)
                print("\t")
            }
            if (rowIndex !== this.size) println()
        }
    }

    fun markCell(x: Int, y: Int, value: Value) {
        if (x < this.size && y < this.size) {
            val cell = this.cells[x][y]
            if(cell.isEmpty()) {
                cell.setValue(value)
                return
            }
            throw FieldException("Cell is not empty")
        }
        throw FieldException("X and Y must be less than the size of the field")
    }

    private fun isRowWin(value: Value): Boolean {
        for (i in 0 until this.size) {
            val cell = this.cells[0][i]
            if (cell.getValue() != value) {
                return false
            }
        }
        return true
    }

    private fun isColWin(value: Value): Boolean {
        for (i in 0 until this.size) {
            val cell = this.cells[i][0]
            if (cell.getValue() != value) {
                return false
            }
        }
        return true
    }

    private fun isDigWin(value: Value): Boolean {
        for (i in 0 until this.size) {
            val cell = this.cells[i][i]
            if (cell.getValue() != value) {
                return false
            }
        }
        return true
    }

    fun isWin(value: Value): Boolean {
        return this.isRowWin(value) || this.isColWin(value) || this.isDigWin(value)
    }

    fun restart() {
        this.cells.forEach { row -> row.forEach { cell -> cell.restart() } }
    }
}