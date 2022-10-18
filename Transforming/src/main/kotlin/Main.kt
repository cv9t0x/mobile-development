fun main() {
    val figures: ArrayList<Movable> = arrayListOf()
    val movable: Movable = Rect(0, 0, 1, 1)
    movable.move(1, 1)

    val f1 = Rect(2, 2, 2, 2)
    val f2 = Circle(0, 0, 2)
    val f3 = Square(1, 1, 2)

    figures.add(f1)
    figures.add(f2)
    figures.add(f3)

    println(f1.area())
    println(f2.area())
    println(f3.area())

    f3.resize(2)

    println(f3.area())

    f3.rotate(RotateDirection.Clockwise, 2, 2)

    println("${f2.x} ${f2.y}")
}