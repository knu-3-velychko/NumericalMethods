import kotlin.math.sqrt

class SeidelMethod(val matrix: Array<Array<Double>>, var x: Array<Double>, val size: Int, val e: Double) {
    val result: Array<Double> by lazy {
        var norm = e * e * 10

        var p = Array(size) { 0.0 }
        while (sqrt(norm) > e) {
            for (i in 0 until size) {
                p[i] = x[i]
            }
            for (i in 0 until size) {
                var tmp = 0.0
                for (j in 0 until i)
                    tmp += matrix[i][j] * x[j]
                for (j in i + 1 until size)
                    tmp += matrix[i][j] * p[j]
                x[i] = (matrix[i][size] - tmp) / matrix[i][i]
            }
            norm = 0.0
            for (i in 0 until size)
                norm += (x[i] - p[i]) * (x[i] - p[i])
        }
        x
    }

}