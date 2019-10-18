class JacobiMethod(
    val matrix: Matrix,
    private var b: Array<Double>,
    private var x: Array<Double>,
    val size: Int,
    val e: Double
) :
    Method {
    override val result: Array<Double> by lazy {
        val M = matrix.getDiagonal()
        val N = (-1.0 * (matrix - matrix.getDiagonal()))

        do {

        } while (true)


        b
    }
    override var iterations: Int? = null
}