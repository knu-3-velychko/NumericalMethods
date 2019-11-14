class NewtonPolynomial(private val points: Array<Point>) : Method {
    private val size = points.size
    private val differenceTable = Array(size) { Array(size) { 0.0 } }

    override val result: DoubleArray by lazy {
        val size = points.size
        var res = DoubleArray(size) { 0.0 }
        var factorial = 1.0
        var product = doubleArrayOf(1.0)
        fillDifferenceTable()

        for (i in 0 until size) {
            res += product * differenceTable[i][0]* (1 / factorial)
            factorial *= i + 1
            product *= doubleArrayOf(1.0, -points[i].x)
        }
        res
    }

    private fun fillDifferenceTable() {
        for (i in 0 until size) {
            differenceTable[0][i] = points[i].y
        }
        for (i in 1 until size) {
            for (j in 0 until size - i)
                differenceTable[i][j] =
                    (differenceTable[i - 1][j + 1] - differenceTable[i - 1][j]) / (points[j + 1].x - points[j].x)
        }
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