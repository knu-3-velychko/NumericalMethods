import kotlin.math.absoluteValue

class BisectionMethod(private var a: Float, private var b: Float, private val e: Float, val f: (x: Float) -> Float) :
    Method {
    override fun run(): Float {
        var itNumber = 0
        var c = 0F
        while (b - a > e) {
            itNumber++
            c = (a + b) / 2
            if (f(c).absoluteValue < e)
                break
            if (f(a) * f(c) < 0)
                b = c
            if (f(c) * f(b) < 0)
                a = c
        }
        println("Number of iterations $itNumber")
        return c
    }
}


