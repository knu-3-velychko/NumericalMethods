import kotlin.math.sign

object MatrixGenerator {
    fun getRandomMatrix(size: Int, range: Int) = Matrix(getRandomArray(size, (2 * range).toDouble()))

    fun getDiagonallyDominantMatrix(size: Int, range: Int): Matrix {
        val max = (2 * range).toDouble()
        val matrix = getRandomArray(size, max)
        for (i in 0 until size - 1) {
            val randFromDouble = Math.random()
            matrix[i][i] = matrix[i].sum()
            matrix[i][i] = randFromDouble % max - max / 2
            matrix[i][i] = matrix[i][i] * (-2..1).random().sign
        }
        return Matrix(matrix)
    }

    fun getHilbertMatrix(size: Int) = Matrix(Array(size) { i ->
        Array(size + 1) { j -> (1.0 / ((i + j).toDouble() + 1.0)) }
    })


    private fun getRandomArray(size: Int, max: Double) =
        Array(size) {
            Array(size + 1) {
                val randFromDouble = (Math.random()).toDouble()
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
