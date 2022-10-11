class Settings(val sizeOfField: Int = 3) {}

class Game {
    private var currentPlayer: Value = Value.X

    private var sizeOfField: Int
    private var fields: Array<Array<Field>>
    private var currentField: Field


    constructor(settings: Settings = Settings()) {
        this.sizeOfField = settings.sizeOfField
        this.fields =
            Array<Array<Field>>(this.sizeOfField) { Array(this.sizeOfField) { Field(this.sizeOfField) } }
        this.currentField = this.fields[0][0]
    }

    fun getCurrentPlayer(): Value {
        return this.currentPlayer
    }

    fun printField(x: Int, y: Int) {
        this.fields[x][y].printCells()
    }

    fun printAllFields() {
        for (fstIndex in 0 until this.sizeOfField) {
            for (sndIndex in 0 until this.sizeOfField) {
                for (trdIndex in 0 until this.sizeOfField) {
                    val field = this.fields[fstIndex][trdIndex]
                    for (fthIndex in 0 until this.sizeOfField) {
                        print(field.getCells()[sndIndex][fthIndex])
                        if (fthIndex != this.sizeOfField - 1) print(" ")
                    }
                    if (trdIndex != this.sizeOfField - 1) print("\t")
                }
                println()
            }
            if (fstIndex != this.sizeOfField - 1) println()
        }
    }

    private fun markCell(x: Int, y: Int) {
        this.currentField.markCell(x, y, this.currentPlayer)
    }

    private fun switchPlayer() {
        this.currentPlayer = if (this.currentPlayer == Value.X) Value.O else Value.X
    }

    private fun switchField(x: Int, y: Int) {
        this.currentField = this.fields[y][x]
    }

    fun makeMove(x: Int, y: Int) {
        this.markCell(x, y)
        this.switchPlayer()
        this.switchField(x, y)
    }
}