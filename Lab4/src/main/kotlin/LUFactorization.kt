class LUFactorization(val matrix: Matrix, private var b: Array<Double>, val size: Int, val e: Double) :
    Method {
    override val result: Array<Double> by lazy {
        val u = Matrix(size)
        val l = Matrix(size)

        var sum: Double
        for (i in 0 until size) {
            //get u matrix
            for (j in i until size) {
                sum = 0.0
                for (k in 0 until i)
                    sum += u[i, k] * u[k, j]
                u[i, j] = matrix[i, j] - sum
            }
            //get l matrix
            for (j in i + 1 until size) {
                sum = 0.0
                for (k in 0 until i)
                    sum += l[i, k] * l[k, j]
                l[j, i] = (1.0 / u[i, i]) * (matrix[j, i] - sum)
            }
        }

        //LU = L+U-I

        //Ly = b
        val y = Array(size) { 0.0 }
        for (i in 0 until size) {
            sum = 0.0
            for (k in 0 until i) {
                sum += l[i, k] * y[k]
            }
            y[i] = b[i] - sum
        }

        //Ux = y
        val x = Array(size) { 0.0 }
        for (i in size - 1 downTo 0) {
            sum = 0.0
            for (k in i + 1 until size) {
                sum += u[i, k] * x[k]
            }
            x[i] = (1.0 / u[i, i]) * (y[i] - sum)
        }

        x
    }

    override var iterations: Int? = null
}