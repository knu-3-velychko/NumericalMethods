import kotlin.math.absoluteValue
import kotlin.math.sqrt

class JacobiMethod(val matrix: Matrix, val e: Double) {
    val eigenValues: Array<Double> by lazy {
        var M: Matrix
        var S = Matrix(matrix)
        val size = matrix.size
        var result = Array(size) { 0.0 }

        eigenVectors = getIdentityMatrix(size)

        while (true) {
            M = Matrix(S)
            for (i in 0 until size)
                M[i, i] = 0.0
            var (i, j) = findMax(M)
            if (i == j)
                break

            if (S[i, j].absoluteValue < e)
                break

            val J = S.getRotationMatrix(i, j, e)
            S = J.transpose() * S * J

            eigenVectors = eigenVectors!! * J

        }

        for (i in 0 until size)
            result[i] = S[i, i]
        result
    }

    var eigenVectors: Matrix? = null

    fun findMax(matrix: Matrix): Pair<Int, Int> {
        var row = 0
        var column = 0
        var max = matrix[0, 0]
        for (i in 0 until matrix.size)
            for (j in 0 until matrix.size)
                if (max.absoluteValue < matrix[i, j].absoluteValue) {
                    max = matrix[i, j]
                    row = i
                    column = j
                }
        return row to column
    }

    fun getNorm(matrix: Matrix): Double {
        var norm = 0.0
        for (i in 0 until matrix.size)
            for (j in 0 until matrix.size)
                if (i != j)
                    norm += matrix[i, j] * matrix[i, j]
        return sqrt(norm)
    }
}