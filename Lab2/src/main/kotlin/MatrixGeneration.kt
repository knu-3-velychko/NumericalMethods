import java.math.BigDecimal
import java.util.*
import kotlin.math.sign

object MatrixGenerator {
    fun getRandomMatrix(size: Int, range: Int, root: Array<BigDecimal>): Array<Array<BigDecimal>> {
        val matrix = getRandomArray(size, BigDecimal(2 * range))

        return composeMatrix(size, matrix, getVector(size, matrix, root))
    }

    fun getDiagonallyDominantMatrix(size: Int, range: Int, root: Array<BigDecimal>): Array<Array<BigDecimal>> {
        val max = BigDecimal(2 * range)
        val matrix = getRandomArray(size, max)
        for (i in 0 until size - 1) {
            val randFromDouble = BigDecimal(Math.random())
            matrix[i][i] = matrix[i].fold(BigDecimal.ZERO) { acc, e -> acc + e.abs() }
            matrix[i][i] = randFromDouble.multiply(max) - max / BigDecimal(2)
            matrix[i][i] *= BigDecimal((-2..1).random().sign)
        }
        return composeMatrix(size, matrix, getVector(size, matrix, root))
    }

    fun getHilbertMatrix(size: Int, root: Array<BigDecimal>): Array<Array<BigDecimal>> {
        val matrix = Array(size) { i ->
            Array(size + 1) { j -> BigDecimal(1 / (i + j + 1)) }
        }
        return composeMatrix(size, matrix, getVector(size, matrix, root))
    }

    private fun getRandomArray(size: Int, max: BigDecimal) =
        Array(size) {
            Array(size + 1) {
                val randFromDouble = BigDecimal(Math.random())
                randFromDouble.multiply(max) - max / BigDecimal(2)
            }
        }

    private fun getVector(size: Int, matrix: Array<Array<BigDecimal>>, root: Array<BigDecimal>) =
        Array(size) { i ->
            matrix[i].fold(BigDecimal.ZERO) { acc, e -> acc + e * root[i] }
        }

    private fun composeMatrix(
        size: Int,
        matrix: Array<Array<BigDecimal>>,
        vector: Array<BigDecimal>
    ): Array<Array<BigDecimal>> {
        for (i in 0 until size) {
            matrix[i][size] = vector[i]
        }
        return matrix
    }
}