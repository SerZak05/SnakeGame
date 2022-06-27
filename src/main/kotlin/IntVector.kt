data class IntVector(val x : Int, val y : Int) {
    operator fun plus(other : IntVector) = IntVector(x + other.x, y + other.y)

    override fun toString() = "($x, $y)"
}

fun getDirVector(dir : Int) : IntVector = when(dir) {
    0 -> IntVector(1, 0)
    1 -> IntVector(0, 1)
    2 -> IntVector(-1, 0)
    3 -> IntVector(0, -1)
    else -> throw Exception("Invalid direction!")
}