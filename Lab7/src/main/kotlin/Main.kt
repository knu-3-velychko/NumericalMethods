import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder
import kotlin.math.sin

class Main {
    companion object {
        private fun printResult(name: String, method: Method, range: Iterable<Double>) {

            println()
            println()
            println(f(1.0))
            println(getPolynomialRoot(1.0, method.result))
            println()
            println()
            // Create Chart

            val points = fillArray(-3.0..3.0 step 0.001) { x: Double -> f(x) }.toTypedArray()
            val (x1Data, y1Data) = points.split()
            val y2Data = fillArrayPolynomial(-3.0..3.0 step 0.001, method.result).toDoubleArray()
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


        private fun f(x: Double) = x * sin(5 * x)

        @JvmStatic
        fun main(args: Array<String>) {
            val range = -3.0..3.0 step 0.2
            val points = fillArray(range) { x: Double -> f(x) }

            val lagrangePolynomial = LagrangePolynomial(points.toTypedArray())
            printResult(
                "Lagrange Polynomial Interpolation ",
                lagrangePolynomial,
                range
            )

            val newtonPolynomial = NewtonPolynomial(points.toTypedArray())
            printResult(
                "Lagrange Polynomial Interpolation ",
                newtonPolynomial,
                range
            )
            println("It works! ")
        }

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

        private infix fun ClosedRange<Double>.step(step: Double): Iterable<Double> {
            require(start.isFinite())
            require(endInclusive.isFinite())
            require(step > 0.0) { "Step must be positive, was: $step." }
            val sequence = generateSequence(start) { previous ->
                if (previous == Double.POSITIVE_INFINITY) return@generateSequence null
                val next = previous + step
                if (next > endInclusive) null else next
            }
            return sequence.asIterable()
        }
    }

}