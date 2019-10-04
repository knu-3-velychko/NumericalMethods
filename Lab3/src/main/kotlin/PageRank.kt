class PageRank(val fName: String, val e: Double) {
    var size = 0

    val alpha = 0.75
    val A: Matrix by lazy {
        Matrix(fName)
    }
    val R: Matrix by lazy {
        size = A.size
        val array = Array(size) {
            Array(size) { 1.0 / (size).toDouble() }
        }
        Matrix(array)
    }

    val M: Matrix by lazy {
        alpha * A + (1 - alpha) * R
    }

    val result: Array<Double> by lazy {
        var matrix = M
        var prev = Array(size) { 1 / (size).toDouble() }
        var x = matrix * prev
        while (norm(x, prev) > e) {
            prev = x
            x = matrix * prev
        }
        x
    }

    val maxValue: Double? by lazy {
        result.max()
    }

    val pos: Int? by lazy {
        result.indexOf(maxValue)
    }

    private fun norm(v1: Array<Double>, v2: Array<Double>): Double {
        var norm = 0.0
        for (i in v1.indices)
            norm += (v1[i] - v2[i]) * (v1[i] - v2[i])
        return norm
    }
}