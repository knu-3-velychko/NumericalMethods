class NewtonPolynomial(val points: Array<Point>) : Method {
    override val result: DoubleArray by lazy {
        val size = points.size
        var res = DoubleArray(size) { 0.0 }
        var product = doubleArrayOf(1.0)


        for (i in 0 until size) {
            res += product * getDividedDifference(i, 0)
            product *= doubleArrayOf(1.0, -points[i].x)
        }
        res
    }

    private fun getDividedDifference(pow: Int, index: Int): Double {
        if (pow == 0)
            return points[index].y
        return (getDividedDifference(pow - 1, index + 1) -
                getDividedDifference(pow - 1, index)) /
                (points[index + 1].x - points[index].x)
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