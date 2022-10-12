import java.lang.Exception
import kotlin.math.roundToInt

class Settings(var sizeOfField: Int = 3)

class GameException(message: String) : Exception(message)

val defaultSettings = Settings(3)

enum class Command {
    PRINT_ALL_FIELDS, PRINT_CURRENT_FIELD, MAKE_MOVE, RETURN
}

val commands = mapOf(
    "print all map" to Command.PRINT_ALL_FIELDS,
    "print current field" to Command.PRINT_CURRENT_FIELD,
    "make move" to Command.MAKE_MOVE,
    "return" to Command.RETURN
)

class Game {
    private var currentPlayer = Value.X
    private var gameOver = false

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

    private fun printCurrentField() {
        println("x: ${this.currentField.getX()}, y: ${this.currentField.getY()}")
        this.currentField.printCells()
    }

    private fun printAllFields() {
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
        if (x < this.sizeOfField && y < this.sizeOfField) {
            this.currentField = this.fields[y][x]
            return
        }
        throw GameException("X and Y must be less than the size of the field")
    }

    private fun checkWin() {
        if (this.currentField.isWin(this.currentPlayer)) this.gameOver = this.currentField.isWin(this.currentPlayer)
    }

    private fun makeMove(x: Int, y: Int) {
        this.markCell(x, y)
        this.checkWin()
        this.switchPlayer()
        this.switchField(x, y)
    }


    private fun restart() {
        this.currentPlayer = Value.X
        this.gameOver = false
        this.currentField = this.fields[0][0]
        this.fields.forEach { row -> row.forEach { field -> field.restart() } }
    }

    // Лучше конечно вынести обработчик для консоли в отдельный класс, но мне было честно сказать лень
    private fun startLoop() {
        println("Game started!")
        while (!this.gameOver) {
            println("Enter one of the commands for actions. Available actions - print all map, print current field, make move:")
            var input = readLine().toString()
            var command = commands[input]
            if (command != null) {
                if (command == Command.PRINT_ALL_FIELDS) this.printAllFields()
                if (command == Command.PRINT_CURRENT_FIELD) this.printCurrentField()
                if (command == Command.MAKE_MOVE) {
                    println("Current field:")
                    this.printCurrentField()
                    println("Input x and y or enter return to go back to the menu:")
                    input = readLine().toString()
                    var command = commands[input]
                    if(command == Command.RETURN) continue
                    try {
                        val (x, y) = input.toString().split(" ").map { it.toInt() }
                        this.makeMove(x, y)
                    } catch (e: Exception) {
                        if(e is FieldException || e is GameException) println(e.message) else println("Unknown command")
                    }
                }
                continue
            }
            println("Unknown command")
        }
        println("Congratulations ${this.currentPlayer} won!")
    }

    fun start() {
        this.startLoop()
        this.restart()
    }
}