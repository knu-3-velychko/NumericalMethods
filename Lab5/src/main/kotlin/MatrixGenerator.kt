import kotlin.math.absoluteValue
import kotlin.math.sign

object MatrixGenerator {
    fun getRandomMatrix(size: Int, range: Int) = Matrix(getRandomArray(size, (2 * range).toDouble()))

    fun getDiagonallyDominantMatrix(size: Int, range: Int): Matrix {
        val max = (2 * range).toDouble()
        val matrix = getRandomArray(size, max)
        for (i in 0 until size) {
            val randFromDouble = Math.random()
            var sum = 0.0
            for (j in matrix[i])
                sum += j.absoluteValue
            matrix[i][i] = sum
            matrix[i][i] += randFromDouble % (max / 2)
            matrix[i][i] = matrix[i][i] *
                    if ((-2..1).random() >= 0) 1 else -1
        }
        return Matrix(matrix)
    }

    fun getHilbertMatrix(size: Int) = Matrix(Array(size) { i ->
        Array(size + 1) { j -> (1.0 / ((i + j).toDouble() + 1.0)) }
    })

    fun getSymmetricMatrix(size: Int, range: Int): Matrix {
        val max = (2 * range).toDouble()
        val result = Array(size) {
            Array(size) { 0.0 }
        }
        for (i in 0 until size)
            for (j in 0 until i) {
                val randFromDouble = (Math.random())
                result[i][j] = randFromDouble % max - max / 2
                result[j][i] = result[i][j]
            }
        return Matrix(result)
    }


    private fun getRandomArray(size: Int, max: Double) =
        Array(size) {
            Array(size + 1) {
                val randFromDouble = (Math.random())
                randFromDouble % max - max / 2
            }
        }

    fun getVector(size: Int, matrix: Matrix, root: Array<Double>) =
        Array(size) { i ->
            matrix[i].foldIndexed(0.0) { index, acc, d ->
                acc +
                        if (index != size) d * root[index] else 0.0
            }
        }
}
