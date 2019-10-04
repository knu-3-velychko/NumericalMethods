class PageRank(val fName: String) {
    var size = 0

    val alpha = 0.75
    val A: Matrix by lazy {
        Matrix(fName)
    }
    val R: Matrix by lazy {
        size = A.size
        val array = Array(size) {
            Array(size) { (1 / size).toDouble() }
        }
        Matrix(array)
    }

    val M: Matrix by lazy {
        alpha * A + (1 - alpha) * R
    }

//    val result: Array<Double> by lazy {
//
//    }

}