import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.Objects.isNull

const val FILE_PATH = "src/main/kotlin/text.txt"

fun main(args: Array<String>) {
    val file = File(FILE_PATH)
    val bufferedReader = BufferedReader(FileReader(file))

    var sizeOfArray: Number? = null
    var line: String

    var array: Array<Double> = arrayOf()

    while(true) {
        line = bufferedReader.readLine() ?: break

        if(isNull(sizeOfArray)) {
            val parsedInt = line.toInt()
            sizeOfArray = parsedInt
        } else {
            array = line.split(" ").map { it.toDouble() }.toTypedArray()
        }
    }

    array.mapIndexed { index, it ->
        if(index == 0 || index == array.size - 1) it else (array[index + 1] + it + array[index - 1]) / 3 }
        .forEach { println(it) }
}