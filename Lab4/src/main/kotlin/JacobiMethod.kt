class JacobiMethod(
    val matrix: Matrix,
    private var b: Array<Double>,
    private var x: Array<Double>,
    val size: Int,
    val e: Double
) :
    Method {
    override val result: Array<Double> by lazy {
        val M = matrix.getDiagonalInvert()
        val N = (matrix - matrix.getDiagonal())

        var norm = 1.0
        var nextX: Array<Double>

        iterations = 0
        while (norm > e) {
            iterations = iterations?.inc()
            nextX = M * (b + (-1.0 * N) * x)
            norm = 0.0
            for (i in 0 until size)
                norm += (x[i] - nextX[i]) * (x[i] - nextX[i])
            x = nextX
        }
        x
    }
    override var iterations: Int? = null
}