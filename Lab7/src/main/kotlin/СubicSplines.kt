class СubicSplines(val function: Array<Point>) : Method {
    override val result: DoubleArray by lazy {
        val size = function.size
        val res = DoubleArray(size) { 0.0 }

        res
    }

}