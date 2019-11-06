import kotlin.math.sin

class Main {
    companion object {
        private fun printResult(name: String, method: Method) {
        }

        fun f(x: Double) = x * sin(5 * x)

        @JvmStatic
        fun main(args: Array<String>) {
            val range = -3.0..3.0 step 0.1

            println("It works! ")
        }

        infix fun ClosedRange<Double>.step(step: Double): Iterable<Double> {
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