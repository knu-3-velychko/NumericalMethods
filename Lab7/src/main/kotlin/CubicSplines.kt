import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder

class CubicSplines(val points: Array<Point>) {
    private val differenceTable = Array(3) { DoubleArray(points.size) { 0.0 } }

    private val result: Array<DoubleArray> by lazy {
        val size = points.size
        val res = Array(4) { DoubleArray(size) { 0.0 } }
        val h = DoubleArray(size) { 0.0 }

        res[0][0] = points[0].y
        for (i in 1 until size) {
            h[i] = points[i].x - points[i - 1].x
            res[0][i] = points[i].y
        }

        val c = getC(size, h)
        for (i in 0 until size) {
            res[2][i] = if (i == 0 || i == size - 1) 0.0 else c[i - 1]
        }

        res[1][1] = res[2][1] * h[1] / 3.0 + differenceTable[1][0]
        res[3][1] = res[2][1] / h[1]
        for (i in 2 until size) {
            res[1][i] = res[2][i] * h[i] / 3.0 + res[2][i - 1] * h[i] / 6.0 + differenceTable[1][i - 1]
            res[3][i] = (res[2][i] - res[2][i - 1]) / h[i]
        }

        res
    }

    fun printResults(range: Iterable<Double>, array: Array<Point>) {
        val x = mutableListOf<Double>()
        val y = mutableListOf<Double>()
        var j = 0

        for (i in range) {
            x.add(i)
            if (points[j].x < i && j + 1 != points.size)
                j++
            y.add(getValue(i, j))
        }

        val chart =
            XYChartBuilder().width(600).height(400).title("Cubic Splines").xAxisTitle("X").yAxisTitle("Y").build()


        val (x1Data, y1Data) = array.split()

        chart.addSeries(
            "f1(x)",
            x1Data, y1Data
        )

        chart.addSeries(
            "f2(x)",
            x.toDoubleArray(), y.toDoubleArray()
        )

        val sw = SwingWrapper(chart)

        sw.displayChart()
    }

    fun getValue(x: Double, i: Int): Double {
        var res = 0.0
        res += result[0][i]
        res += result[1][i] * (x - points[i].x)
        res += result[2][i] * pow(x - points[i].x, 2) / 2.0
        res += result[3][i] * pow(x - points[i].x, 3) / 6.0
        return res
    }

    private fun pow(x: Double, pow: Int): Double {
        var res = 1.0
        for (i in 0 until pow) {
            res *= x
        }
        return res
    }

    private fun getC(size: Int, h: DoubleArray): DoubleArray {
        val algorithm = ThomasAlgorithm(fillMatrix(size, h))
        return algorithm.getResult()
    }

    private fun fillMatrix(size: Int, h: DoubleArray): Array<Array<Double>> {
        val matrix = Array(size - 2) { Array(size - 1) { 0.0 } }
        for (i in 1 until size - 2) {
            matrix[i][i - 1] = h[i + 1] / (h[i + 1] + h[i + 2])
            matrix[i][i] = 2.0
            matrix[i][i + 1] = h[i + 2] / (h[i + 1] + h[i + 2])
        }
        matrix[0][0] = 2.0
        matrix[0][1] = h[2] / (h[1] + h[2])

        matrix[size - 3][size - 3] = 2.0
        matrix[size - 3][size - 4] = h[size - 2] / (h[size - 2] + h[size - 1])

        getDifferences(size)
        for (i in 0 until size - 2) {
            matrix[i][size - 2] = 6.0 * differenceTable[2][i]
        }

        return matrix
    }

    private fun getDifferences(size: Int) {
        for (i in 0 until size) {
            differenceTable[0][i] = points[i].y
        }
        for (i in 1 until 3) {
            for (j in 0 until size - i)
                differenceTable[i][j] =
                    (differenceTable[i - 1][j + 1] - differenceTable[i - 1][j]) / (points[j + 1].x - points[j].x)
        }
    }
}
