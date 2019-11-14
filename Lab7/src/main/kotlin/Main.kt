import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder
import kotlin.math.sin

class Main {
    companion object {
        private fun printResult(name: String, method: Method, range: Iterable<Double>) {

            // Create Chart

            val points = fillArray(range) { x: Double -> f(x) }.toTypedArray()
            val (x1Data, y1Data) = points.split()
            val y2Data = fillArrayPolynomial(range, method.result).toDoubleArray()
            val chart =
                XYChartBuilder().width(600).height(400).title("Area Chart").xAxisTitle("X").yAxisTitle("Y").build()

            chart.addSeries(
                "f1(x)",
                x1Data, y1Data
            )

            chart.addSeries(
                "f2(x)",
                x1Data, y2Data
            )

            val sw = SwingWrapper(chart)

            sw.displayChart()
        }

        private fun printSplines(name: String, method: CubicSplines, range: Iterable<Double>) {
            // Create Chart

            val points = fillArray(range) { x: Double -> f(x) }.toTypedArray()
            method.printResults(range, points)

        }


        private fun f(x: Double) = x * sin(5 * x)

        private fun evenly() {
            val range = -3.0..3.0 step 0.0001
            val range1 = -3.0..3.0 step 0.2
            val points1 = fillArray(range1) { x: Double -> f(x) }

            val lagrangePolynomial = LagrangePolynomial(points1.toTypedArray())
            printResult(
                "Lagrange Polynomial Interpolation ",
                lagrangePolynomial,
                range
            )
            val range2 = -3.0..3.0 step 0.1
            val points2 = fillArray(range2) { x: Double -> f(x) }

            val newtonPolynomial = NewtonPolynomial(points2.toTypedArray())
            printResult(
                "Lagrange Polynomial Interpolation ",
                newtonPolynomial,
                range
            )

            val cubicSplines = CubicSplines(points2.toTypedArray())
            printSplines(
                "Cubic Splines Interpolation ",
                cubicSplines,
                range
            )
            println("It works! ")
        }

        private fun chebyshev() {
            val n = 30
            val roots = ChebyshevPolynomialRoots()
            val points = getPoints(roots.getRoots(n)) { x: Double -> f(x) }
            val range = -1.0..1.0 step 0.0001

            val lagrangePolynomial = LagrangePolynomial(points)
            printResult(
                "Lagrange Polynomial Interpolation ",
                lagrangePolynomial,
                range
            )

            val newtonPolynomial = NewtonPolynomial(points)
            printResult(
                "Lagrange Polynomial Interpolation ",
                newtonPolynomial,
                range
            )

            val cubicSplines = CubicSplines(points)
            printSplines(
                "Cubic Splines Interpolation ",
                cubicSplines,
                range
            )
            println("It works! ")
        }

        @JvmStatic
        fun main(args: Array<String>) {
            //evenly()
            chebyshev()
        }

        private fun getPoints(x: DoubleArray, f: (Double) -> Double) =
            Array(x.size) { i -> Point(x[i], f(x[i])) }


        private fun fillArray(range: Iterable<Double>, f: (Double) -> Double): MutableList<Point> {
            val array = mutableListOf<Point>()
            for (i in range) {
                array.add(Point(i, f(i)))
            }
            return array

        }

        private fun fillArrayPolynomial(range: Iterable<Double>, coef: DoubleArray): MutableList<Double> {
            val result = mutableListOf<Double>()
            for (i in range) {
                result.add(getPolynomialRoot(i, coef))
            }
            return result
        }

        private fun getPolynomialRoot(x: Double, coef: DoubleArray): Double {
            var pow = coef.size - 1
            var result = 0.0
            for (i in coef.indices) {
                result += power(x, pow) * coef[i]
                pow--
            }
            return result
        }

        private fun power(x: Double, pow: Int): Double {
            var res = 1.0
            for (i in 0 until pow)
                res *= x
            return res
        }
    }

}