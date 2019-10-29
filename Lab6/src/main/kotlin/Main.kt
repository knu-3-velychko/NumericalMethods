import org.knowm.xchart.BitmapEncoder.BitmapFormat
import org.knowm.xchart.BitmapEncoder
import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.QuickChart

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("I luv ya vawy much <3 Vawy not like wavy plasters, like very OwO")

            val xData = doubleArrayOf(0.0,  2.0)
            val yData = doubleArrayOf(2.0, 0.0)

// Create Chart
            val chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData)

// Show it
            SwingWrapper(chart).displayChart()

// Save it
            BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapFormat.PNG)

// or save it in high-res
            BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapFormat.PNG, 300)
        }
    }

}