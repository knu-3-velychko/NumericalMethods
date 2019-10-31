class Vector2D {
    val size = 2
    var x: Double
        get() {
            return array[0]
        }
        set(value) {
            array[0] = value
        }
    var y: Double
        get() {
            return array[1]
        }
        set(value) {
            array[1] = value
        }
    private var array = DoubleArray(size)

    constructor(x: Double, y: Double) {
        array[0] = x
        array[1] = y
    }

    constructor(array: DoubleArray) {
        this.array = array.clone()
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

    operator fun set(index: Int, value: Double) {
        array[index] = value
    }

    operator fun times(value: Double): Vector2D = Vector2D(this.x * value, this.y * value)
}

fun dot(vector1: Vector2D, vector2: Vector2D): Double {
    var result = 0.0
    for (i in 0 until vector1.size)
        result += vector1[i] * vector2[i]
    return result
}

operator fun Double.times(vector: Vector2D): Vector2D {
    return vector * this
}