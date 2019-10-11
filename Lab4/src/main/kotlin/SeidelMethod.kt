class SeidelMethod(
    val matrix: Array<Array<Double>>,
    private var b: Array<Double>,
    private var x: Array<Double>,
    val size: Int,
    val e: Double
) :
    Method {
    override val result: Array<Double> by lazy {
        val MInv = Matrix(Array(size) { Array(size) { 0.0 } })
        val N = Matrix(Array(size) { Array(size) { 0.0 } })


        var nextX = x
        do {
            nextX = MInv * (b + N * x)
            var norm = 0.0
            for (i in 0 until size)
                norm += (x[i] - nextX[i]) * (x[i] - nextX[i])
        } while (norm > e)

        b
    }
    override var iterations: Int? = null
}