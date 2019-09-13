import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.sin
import kotlin.math.tan
import kotlin.system.measureTimeMillis

class Main {
    companion object {

        val colorReset = "\u001B[0m"
        val colorYellow = "\u001B[33m"
        val colorCyan = "\u001B[36m"

        private fun function1(x: Double) = sin(x)
        private fun derivativeFunction1(x: Double) = cos(x)

        private fun function2(x: Double) = x * x - 4
        private fun derivativeFunction2(x: Double) = 2 * x

        private fun function3(x: Double) = x * x + 4
        private fun derivativeFunction3(x: Double) = 2 * x

        private fun printResult(name: String, m: Method) {
            println("$colorCyan$name$colorReset result: $colorYellow" + (m.result ?: "no result"))
            println(
                "$colorCyan$name$colorReset number of iterations: $colorYellow" + (m.iterations
                    ?: "not calculated")
            )
            println("$colorCyan$name$colorReset time: $colorYellow" + (m.time ?: "not calculated"))
            println()

        }

        fun run(
            a: Double,
            b: Double,
            e: Double,
            f: (x: Double) -> Double,
            derivative: (x: Double) -> Double
        ) {
            var max = derivative(0.0)
            var min = derivative(0.0)
            for (i in a..b step 0.01) {
                var tmp = derivative(i)
                if (max < tmp)
                    max = tmp
                if (min < tmp)
                    min = tmp
            }
            val c = 2 / (max + min)

            val bisectionMethod = BisectionMethod(a, b, e, f)
            val newtonMethod = NewtonMethod((a + b) / 2, e, f, derivative)
            val relaxationMethod = RelaxationMethod(a, b, c, e, f, derivative)

            printResult("Bisection method", bisectionMethod)
            printResult("Newton method", newtonMethod)
            printResult("Relaxation method", relaxationMethod)
        }

        @JvmStatic
        fun main(args: Array<String>) {
//            run(-0.9, 1.0, 0.0001, ::function1, ::derivativeFunction1)

          //  run(0.9, 3.0, 0.0001, ::function2, ::derivativeFunction2)

            run(0.9, 3.0, 0.0001, ::function3, ::derivativeFunction3)
        }
    }
}