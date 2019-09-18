import java.math.BigDecimal
import kotlin.math.sign

object MatrixGenerator {
    fun getRandomMatrix(size: Int, range: Int) =
        Matrix(getRandomArray(size, BigDecimal(2 * range)))

    fun getDiagonallyDominantMatrix(size: Int, range: Int): Matrix {
        val max = BigDecimal(2 * range)
        val matrix = getRandomArray(size, max)
        for (i in 0 until size - 1) {
            val randFromDouble = BigDecimal(Math.random())
            matrix[i][i] = matrix[i].fold(BigDecimal.ZERO) { acc, e -> acc + e.abs() }
            matrix[i][i] = randFromDouble.multiply(max) - max / BigDecimal(2)
            matrix[i][i] *= BigDecimal((-2..1).random().sign)
        }
        return Matrix(matrix)
    }

    fun getHilbertMatrix(size: Int) =
        Array(size) { i ->
            Array(size) { j -> BigDecimal(1 / (i + j + 1)) }
        }

    private fun getRandomArray(size: Int, max: BigDecimal) =
        Array(size) {
            Array(size) {
                val randFromDouble = BigDecimal(Math.random())
                randFromDouble.multiply(max) - max / BigDecimal(2)
            }
        }

    fun getVector(size: Int, matrix: Matrix, root: Array<BigDecimal>) =
        Array(size) { i ->
            matrix.matrix[i].fold(BigDecimal.ZERO) { acc, e -> acc + e * root[i] }
        }
}