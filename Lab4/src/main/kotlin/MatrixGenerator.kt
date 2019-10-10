import kotlin.math.sign

object MatrixGenerator {
    fun getRandomMatrix(size: Int, range: Int, root: Array<Double>): Array<Array<Double>> {
        val matrix = getRandomArray(size, (2 * range).toDouble())

        return composeMatrix(size, matrix, getVector(size, matrix, root))
    }

    fun getDiagonallyDominantMatrix(size: Int, range: Int, root: Array<Double>): Array<Array<Double>> {
        val max = (2 * range).toDouble()
        val matrix = getRandomArray(size, max)
        for (i in 0 until size - 1) {
            val randFromDouble = Math.random()
            matrix[i][i] = matrix[i].sum()
            matrix[i][i] = randFromDouble % max - max / 2
            matrix[i][i] = matrix[i][i] * (-2..1).random().sign
        }
        return composeMatrix(size, matrix, getVector(size, matrix, root))
    }

    fun getHilbertMatrix(size: Int, root: Array<Double>): Array<Array<Double>> {
        val matrix = Array(size) { i ->
            Array(size + 1) { j -> (1.0 / ((i + j).toDouble() + 1.0)) }
        }
        return composeMatrix(size, matrix, getVector(size, matrix, root))
    }

    private fun getRandomArray(size: Int, max: Double) =
        Array(size) {
            Array(size + 1) {
                val randFromDouble = (Math.random()).toDouble()
                randFromDouble % max - max / 2
            }
        }

    fun getVector(size: Int, matrix: Array<Array<Double>>, root: Array<Double>) =
        Array(size) { i ->
            matrix[i].foldIndexed(0.0) { index, acc, d ->
                acc +
                        if (index != size) d * root[index] else 0.0
            }
        }

    private fun composeMatrix(
        size: Int,
        matrix: Array<Array<Double>>,
        vector: Array<Double>
    ): Array<Array<Double>> {
        for (i in 0 until size) {
            matrix[i][size] = vector[i]
        }
        return matrix
    }
}
