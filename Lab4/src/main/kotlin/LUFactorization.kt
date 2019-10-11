class LUFactorization(val matrix: Array<Array<Double>>, private var b: Array<Double>, val size: Int, val e: Double) :
    Method {
    override val result: Array<Double> by lazy {
        var lu = Array(size) { Array(size) { 0.0 } }
        var sum: Double
        for (i in 0 until size) {
            //get u matrix
            for (j in i until size) {
                sum = 0.0
                for (k in 0 until i)
                    sum += lu[i][k] * lu[k][j]
                lu[i][j] = matrix[i][j] - sum
            }
            //get l matrix
            for (j in i + 1 until size) {
                sum = 0.0
                for (k in 0 until i)
                    sum += lu[i][k] * lu[k][j]
                lu[j][i] = (1.0 / lu[i][i]) * (matrix[j][i] - sum)
            }
        }

        //LU = L+U-I

        //Ly = b
        val y = Array(size) { 0.0 }
        for (i in 0 until size) {
            sum = 0.0
            for (k in 0 until i) {
                sum += lu[i][k] * y[k]
            }
            y[i] = b[i] - sum
        }

        //Ux = y
        val x = Array(size) { 0.0 }
        for (i in size - 1 downTo  0) {
            sum = 0.0
            for (k in i + 1 until size) {
                sum += lu[i][k] * x[k]
            }
            x[i] = (1.0 / lu[i][i]) * (y[i] - sum)
        }

        x
    }

    override var iterations: Int? = null
}