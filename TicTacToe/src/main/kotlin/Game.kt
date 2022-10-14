import java.lang.Exception
import kotlin.math.roundToInt

class Settings(var sizeOfField: Int = 3)

class GameException(message: String) : Exception(message)

enum class Command {
    PRINT_ALL_FIELDS, PRINT_CURRENT_FIELD, PRINT_CURRENT_PLAYER
}

val commands = mapOf(
    "show all fields" to Command.PRINT_ALL_FIELDS,
    "show current field" to Command.PRINT_CURRENT_FIELD,
    "show current player" to Command.PRINT_CURRENT_PLAYER,
)

val defaultSettings = Settings(3)

class Game {
    private var currentPlayer = Value.X
    private var winner: Value? = null

    private var sizeOfField: Int
    private var fields: Array<Array<Field>>
    private var currentField: Field

    constructor(settings: Settings = defaultSettings) {
        this.sizeOfField = settings.sizeOfField
        this.fields =
            Array<Array<Field>>(this.sizeOfField) { y ->
                Array(this.sizeOfField) { x ->
                    Field(
                        x,
                        y,
                        this.sizeOfField
                    )
                }
            }
        val center = (this.sizeOfField / 2).toDouble().roundToInt()
        this.currentField = this.fields[center][center]
    }

    private fun printCurrentPlayer() {
        println(this.currentPlayer)
    }

    private fun printCurrentField() {
        println("x: ${this.currentField.getX()}, y: ${this.currentField.getY()}")
        this.currentField.printCells()
    }

    private fun printAllFields(withoutCurrent: Boolean = false) {
        for (fstIndex in 0 until this.sizeOfField) {
            for (sndIndex in 0 until this.sizeOfField) {
                for (trdIndex in 0 until this.sizeOfField) {
                    val field = this.fields[fstIndex][trdIndex]
                    val isCurrent = field === this.currentField && !withoutCurrent
                    for (fthIndex in 0 until this.sizeOfField) {
                        field.getCells()[sndIndex][fthIndex].print(isCurrent)
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
        if (x < this.sizeOfField && y < this.sizeOfField) {
            this.currentField = this.fields[y][x]
            return
        }
        throw GameException("X and Y must be less than the size of the field")
    }

    private fun checkWin() {
        if (this.currentField.isWin(this.currentPlayer)) {
            this.winner = this.currentPlayer
        }
    }

    private fun makeMove(x: Int, y: Int) {
        this.markCell(x, y)
        this.checkWin()
        this.switchPlayer()
        this.switchField(x, y)
    }


    private fun restart() {
        this.currentPlayer = Value.X
        this.winner = null
        val center = (this.sizeOfField / 2).toDouble().roundToInt()
        this.currentField = this.fields[center][center]
        this.fields.forEach { row -> row.forEach { field -> field.restart() } }
    }

    // Лучше конечно вынести обработчик для консоли в отдельный класс, но мне было честно сказать лень
    private fun start() {
        println("Game started!")
        while (this.winner == null) {
            println(
                "Enter x and y or one of the commands for actions. Available actions - ${
                    commands.keys.joinToString(
                        ", "
                    )
                }:"
            )
            var input = readLine().toString()
            var command = commands[input]
            try {
                if (command == Command.PRINT_ALL_FIELDS) this.printAllFields()
                if (command == Command.PRINT_CURRENT_FIELD) this.printCurrentField()
                if (command == Command.PRINT_CURRENT_PLAYER) this.printCurrentPlayer()
                if (command == null) {
                    val (x, y) = input.split(" ").map { it.toInt() }
                    this.makeMove(x, y)
                    this.printAllFields()
                }
            } catch (e: Exception) {
                if (e is FieldException || e is GameException) println(e.message) else println("Wrong command. Try again.")
            }
        }
        this.printAllFields(true)
        println("Congratulations ${this.winner} won!")
    }

    fun play() {
        this.start()
        this.restart()
    }
}