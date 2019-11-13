class LagrangePolynomial(private val points: Array<Point>) : Method {
    override val result: DoubleArray by lazy {
        val size = points.size
        var res = DoubleArray(size) { 0.0 }
        for (i in points.indices) {
            res += getCoefs(i)
        }
        res
    }

    private fun getCoefs(index: Int): DoubleArray {
        var array = DoubleArray(1) { 1.0 }
        var product = 1.0
        for (i in points.indices) {
            if (i == index) continue
            val currentTerm = doubleArrayOf(1.0, -points[i].x)
            product *= points[index].x - points[i].x
            array *= currentTerm
        }
        return array * (points[index].y / product)
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
