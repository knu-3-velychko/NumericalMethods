class Vector2D(val x: Double, val y: Double) {
    val size = 2
    private val array = DoubleArray(size)

    init {
        array[0] = x
        array[1] = y
    }

    operator fun plus(vector: Vector2D): Vector2D {
        val x1 = this.x + vector.x
        val y1 = this.y + vector.y
        return Vector2D(x1, y1)
    }

    operator fun minus(vector: Vector2D): Vector2D {
        val x1 = this.x - vector.x
        val y1 = this.y - vector.y
        return Vector2D(x1, y1)
    }

    operator fun get(index: Int): Double {
        if (index > size || index < 0)
            throw IndexOutOfBoundsException("$index is out of range.")
        return array[index]
    }

}

fun dot(vector1: Vector2D, vector2: Vector2D): Double {
    var result = 0.0
    for (i in 0 until vector1.size)
        result += vector1[i] * vector2[i]
    return result
}