class JacobiMethod(
    val matrix: Array<Array<Double>>,
    private var b: Array<Double>,
    private var x: Array<Double>,
    val size: Int,
    val e: Double
) :
    Method {
    override val result: Array<Double> by lazy {
        val MInv = Array(size) { Array(size) { 0.0 } }
        val N: Array<Array<Double>>


        b
    }
    override var iterations: Int? = null
}