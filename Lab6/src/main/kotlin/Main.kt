import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder


class Main {
    companion object {
        private const val colorReset = "\u001B[0m"
        private const val colorYellow = "\u001B[33m"
        private const val colorCyan = "\u001B[36m"

        fun printResult(method: KaczmarzMethod){
            val result=method.getIntersection()
            println("$colorCyan Kaczmarz Method  $colorReset result: $colorYellow ")
            println("( ${result?.x} ${result?.y} )")
        }

        @JvmStatic
        fun main(args: Array<String>) {

            println("I luv ya vawy much <3 Vawy not like wavy plasters, like very OwO")

            val x1Data = doubleArrayOf(2.0, 0.0)
            val y1Data = doubleArrayOf(0.0, 2.0)

            val x2Data = doubleArrayOf(0.0, 2.0)
            val y2Data = doubleArrayOf(0.0, 1.0)

            // Create Chart
            val chart =
                XYChartBuilder().width(600).height(400).title("Area Chart").xAxisTitle("X").yAxisTitle("Y").build()

            chart.addSeries(
                "f1(x)",
                x1Data, y1Data
            )

            chart.addSeries(
                "f2(x)",
                x2Data, y2Data
            )

            val sw = SwingWrapper(chart)

            sw.displayChart()

            val method = KaczmarzMethod(
                Vector2D(x1Data[0], y1Data[0]),
                Vector2D(x1Data[1], y1Data[1]),
                Vector2D(x2Data[0], y2Data[0]),
                Vector2D(x2Data[1], y2Data[1]),
                Vector2D(0.5, 0.5),
                0.00001,
                sw, chart
            )
            printResult(method)
        }
    }

}