import kotlin.math.absoluteValue

class GaussMethod(val matrix: Array<Array<Double>>, val size: Int) {
    val result: Array<Double> by lazy {
        val x = Array(size) { 0.0 }
//        for (i in 0 until size) {
//            for (k in i + 1 until size) {
//                if (matrix[i][i].absoluteValue <= matrix[k][i].absoluteValue)
//                    for (j in 0..size) {
//                        val tmp = matrix[i][j]
//                        matrix[i][j] = matrix[k][j]
//                        matrix[k][j] = tmp
//                    }
//            }
//        }

        for (i in 0 until size - 1) {
            for (k in i + 1 until size) {
                val t = (matrix[k][i] / matrix[i][i])
                for (j in 0..size)
                    matrix[k][j] -= t * matrix[i][j]
            }
        }
        for (i in size - 1 downTo 0) {
            x[i] = matrix[i][size]
            for (j in i + 1 until size) {
                if (j != i)
                    x[i] -=  matrix[i][j] * x[j]
            }
            x[i] /= matrix[i][i]
        }
        x
    }
}