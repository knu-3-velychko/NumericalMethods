class SeidelMethod(val matrix: Array<Array<Double>>, val vector: Array<Double>) {
    val result: Array<Double> by lazy{
        vector
    }
}