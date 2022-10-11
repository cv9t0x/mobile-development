class Field {
    private var size: Int
    private var cells: Array<Array<Cell>>

    constructor(size: Int) {
        this.size = size
        this.cells = Array<Array<Cell>>(this.size) { Array(this.size) { Cell() } }
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
        this.cells[x][y].setValue(value)
    }

    private fun isRowWin(value: Value): Boolean {
        var flag = true
        for (i in 0 until this.size) {
            val cell = this.cells[0][i]
            if(cell.getValue() != value) {
                flag = false
                break
            }
        }
        return flag
    }

    private fun isColWin(value: Value): Boolean {
        var flag = true
        for (i in 0 until this.size) {
            val cell = this.cells[i][0]
            if(cell.getValue() != value) {
                flag = false
                break
            }
        }
        return flag
    }

    private fun isDigWin(value: Value): Boolean {
        var flag = true
        for (i in 0 until this.size) {
            val cell = this.cells[i][i]
            if(cell.getValue() != value) {
                flag = false
                break
            }
        }
        return flag
    }

    fun isWin(value: Value): Boolean {
        return this.isRowWin(value) || this.isColWin(value) || this.isDigWin(value)
    }
}