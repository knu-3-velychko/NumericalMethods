import kotlin.math.absoluteValue
import kotlin.math.sqrt

class Matrix {
    var size: Int = 0
    private var matrix: Array<Array<Double>> = arrayOf()

    constructor(size: Int) {
        this.size = size
        matrix = Array(size) { Array(size) { 0.0 } }
    }

    constructor(matrix: Array<Array<Double>>) {
        this.size = matrix.size
        this.matrix = matrix
    }

    operator fun get(i: Int): Array<Double> {
        checkBoundaries(i)
        return matrix[i]
    }

    operator fun get(i: Int, j: Int): Double {
        checkBoundaries(i, j)
        return matrix[i][j]
    }


    operator fun set(i: Int, j: Int, value: Double) {
        checkBoundaries(i, j)
        matrix[i][j] = value
    }

    private fun checkBoundaries(i: Int, j: Int = -1) {
        if (i >= size || j >= size)
            throw IndexOutOfBoundsException(
                "Unable to get value at indexes $i and $j"
            )
    }

    operator fun plus(matrix: Matrix): Matrix {
        if (this.size != matrix.size)
            throw IndexOutOfBoundsException(
                "Unable to add matrix with different size"
            )
        val result = Matrix(size)
        for (i in 0 until size)
            for (j in 0 until size)
                result[i, j] = this[i, j] + matrix[i, j]
        return result
    }

    operator fun times(matrix: Matrix): Matrix {
        val result = Matrix(size)
        for (i in 0 until size) {
            for (j in 0 until size) {
                for (k in 0 until size) {
                    result[i, j] += this[i, k] * matrix[k, j]
                }
            }
        }
        return result
    }

    operator fun minus(matrix: Matrix): Matrix {
        val result = Matrix(size)
        for (i in 0 until size) {
            for (j in 0 until size) {
                result[i, j] = this[i, j] - matrix[i, j]
            }
        }
        return result
    }

    operator fun times(value: Double): Matrix {
        val result = Matrix(size)
        for (i in 0 until size)
            for (j in 0 until size)
                result[i, j] = this[i, j] * value
        return result
    }

    operator fun times(vector: Array<Double>): Array<Double> {
        val result = Array(size) { 0.0 }
        for (i in 0 until size)
            for (j in 0 until size)
                result[i] += this[i, j] * vector[j]
        return result
    }

    fun transpose(): Matrix {
        val result = Matrix(this.size)
        for (i in 0 until size)
            for (j in 0 until size)
                result[i, j] = this[j, i]
        return result
    }

    fun getRotationMatrix(i: Int, j: Int, e: Double): Matrix {
        val result = getIdentityMatrix(size)
        val theta = (this[i, i] - this[j, j]) / (2 * this[i, j])
        var t = if (theta < e)
            1.0 / (theta.absoluteValue + sqrt(theta * theta + 1.0))
        else
            1.0 / (2.0 * theta).absoluteValue
        t = t.absoluteValue
        val c = 1.0 / sqrt(t * t + 1.0)
        val s = c * t

        result[i, i] = c
        result[j, j] = c

        result[i, j] = s
        result[j, i] = -s

        return result
    }
}


operator fun Double.times(matrix: Matrix) = matrix * this

operator fun Array<Double>.plus(vector: Array<Double>): Array<Double> {
    val result = Array(vector.size) { 0.0 }
    for (i in vector.indices)
        result[i] = this[i] + vector[i]
    return result
}

fun getIdentityMatrix(size: Int): Matrix {
    val matrix = Matrix(size)
    for (i in 0 until size)
        matrix[i, i] = 1.0
    return matrix
}
