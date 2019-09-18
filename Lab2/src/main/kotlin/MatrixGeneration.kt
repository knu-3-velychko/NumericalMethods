import java.math.BigDecimal
import kotlin.math.sign

object MatrixGenerator {
    fun getRandomMatrix(size: Int, range: Int): Matrix {
        return Matrix(getRandomArray(size, BigDecimal(2 * range)))
    }


    fun getDiagonallyDominantMatrix(size: Int, range: Int): Matrix {
        val max = BigDecimal(2 * range)
        var matrix = getRandomArray(size, max)
        for (i in 0 until size - 1) {
            val randFromDouble = BigDecimal(Math.random())
            matrix[i][i] = matrix[i].fold(BigDecimal.ZERO) { acc, e -> acc + e.abs() }
            matrix[i][i] = randFromDouble.multiply(max) - max / BigDecimal(2)
            matrix[i][i] *= BigDecimal((-2..1).random().sign)
        }
        return Matrix(matrix)
    }

    private fun getRandomArray(size: Int, max: BigDecimal): Array<Array<BigDecimal>> {
        val matrix = Array(size) {
            Array(size) {
                val randFromDouble = BigDecimal(Math.random())
                randFromDouble.multiply(max) - max / BigDecimal(2)
            }
        }
        return matrix
    }
}