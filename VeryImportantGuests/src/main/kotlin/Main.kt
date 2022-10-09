import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val t = scanner.nextInt()
    val (n, m) = arrayOf(scanner.nextInt(), scanner.nextInt())

    val res = Array(n) { IntArray(m) }
    var c = 1

    for (i in 0..n+m-2) {
        for (j in 0 until n) {
            val temp = i - j
            if (temp > -1 && temp < m) {
                res[j][temp] = (n * m) - c + 1
                c++
            }
        }
    }

    res.forEach { println(it.joinToString(" ")) }
}