class NewtonPolynomial(private val points: Array<Point>) : Method {
    private val size = points.size

    override val result: DoubleArray by lazy {
        val size = points.size
        var res = DoubleArray(size) { 0.0 }
        val f = DoubleArray(size) { i -> points[i].y }
        var product = doubleArrayOf(1.0)

        for (j in 0 until size)
            for (i in size - 1 downTo j+1)
                f[i] = (f[i] - f[i - 1]) / (points[i].x - points[i - j - 1].x)

        for (i in 0 until size) {
            res += product * f[i]
            product *= doubleArrayOf(1.0, -points[i].x)
        }
        res
    }


    operator fun DoubleArray.plus(array: DoubleArray): DoubleArray {
        val size = if (this.size > array.size) this.size else array.size
        val result = DoubleArray(size) { 0.0 }
        val size1 = this.size
        val size2 = array.size

        for (i in 1..size1) {
            result[size - i] += this[size1 - i]
        }
        for (i in 1..size2) {
            result[size - i] += array[size2 - i]
        }
        return result
    }

}