class LagrangePolynomial(private val points: Array<Point>) : Method {
    override val result: DoubleArray by lazy {
        val size = points.size
        var res = DoubleArray(size) { 0.0 }
        for (i in points.indices) {
            res += getCoefs(i)
        }

        for (i in doubleArrayOf(1.0, -1.0) * doubleArrayOf(1.0, -2.0, 1.0))
            println(i)
        res
    }

    fun getCoefs(index: Int): DoubleArray {
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
        val result = DoubleArray(this.size)
        for (i in this.indices) {
            result[i] = this[i] + array[i]
        }
        return result
    }

}
