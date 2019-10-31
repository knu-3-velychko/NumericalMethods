import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChart
import kotlin.math.atan2

//a and b - 2d points on line
//p - 2d point
class KaczmarzMethod(
    val a1: Vector2D,
    val b1: Vector2D,
    val a2: Vector2D,
    val b2: Vector2D,
    val p: Vector2D,
    val e: Double,
    val sw: SwingWrapper<XYChart>,
    private val chart: XYChart
) {
    val max = 100

    fun getIntersection(): Vector2D? {
        var prevX: Vector2D
        var x = p
        var i = 0
        var a: Vector2D
        var b: Vector2D
        chart.addSeries("chart", doubleArrayOf(p[0]), doubleArrayOf(p[1]), null)

        do {
            prevX = x
            if (i % 2 == 0) {
                a = a1
                b = b1
            } else {
                a = a2
                b = b2
            }

            val ab = b - a
            val ax = x - a

            x = a + (dot(ax, ab) / dot(ab, ab)) * (ab)
            println(x.x)
            println(x.y)
            println()

            Thread.sleep(1000)

            javax.swing.SwingUtilities.invokeLater {
                chart.updateXYSeries("chart", doubleArrayOf(x[0]), doubleArrayOf(x[1]), null)
                sw.repaintChart()
            }
            i++
        } while (norm(prevX, x) > e && i < max)
        if (norm(prevX, x) > e)
            return null
        return x
    }

    private fun norm(a: Vector2D, b: Vector2D): Double {
        var result = 0.0
        for (i in 0 until a.size) {
            result += (a[i] - b[i]) * (a[i] - b[i])
        }
        return result
    }
}