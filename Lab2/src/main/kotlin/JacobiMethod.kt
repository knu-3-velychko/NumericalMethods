import kotlin.math.absoluteValue

class JacobiMethod(val matrix: Array<Array<Double>>, var x: Array<Double>, val size: Int, val e: Double) : Method {
    override val result: Array<Double> by lazy {
        var tmp = Array(size) { 0.0 }
        var norm = e * 10
        iterations = 0
        while (norm > e) {
            iterations = iterations?.inc()
            for (i in 0 until size) {
                tmp[i] = matrix[i][size]
                for (j in 0 until size) {
                    if (i != j)
                        tmp[i] -= matrix[i][j] * x[j]
                }
                tmp[i] /= matrix[i][i]
            }
            norm = (x[0] - tmp[0]).absoluteValue
            for (i in 0 until size) {
                if ((x[i] - tmp[i]).absoluteValue > norm)
                    norm = (x[i] - tmp[i]).absoluteValue
                x[i] = tmp[i]
            }
        }
        x
    }

    override var iterations: Int? = null

}