import java.math.BigDecimal

class GaussMethod(val matrix: Array<Array<BigDecimal>>, val size: Int) {
    val result: Array<BigDecimal> by lazy {
        val size = matrix.size
        for (i in 0 until size) {
            var maxEl = matrix[i][i].abs()
            var maxRow = i
            for (j in i + 1 until size) {
                if (matrix[j][i].abs() > maxEl) {
                    maxEl = matrix[j][i].abs()
                    maxRow = j
                }
            }

            for (j in i..size) {
                val tmp = matrix[maxRow][j]
                matrix[maxRow][j] = matrix[i][j]
                matrix[i][j] = tmp
            }

            for (j in i + 1 until size) {
                val c = -matrix[j][i] / matrix[i][i]
                for (k in i..size) {
                    if (i == k) {
                        matrix[j][k] = BigDecimal(0)
                    } else
                        matrix[j][k] += c * matrix[i][k]
                }
            }
        }

        val x = Array(size) { BigDecimal(0) }
        for (i in size - 1 downTo 0) {
            x[i] = matrix[i][size] / matrix[i][i]
            for (j in i - 1 downTo 0) {
                matrix[j][size] -= matrix[j][i] * x[i]
            }
        }
        x
    }
}