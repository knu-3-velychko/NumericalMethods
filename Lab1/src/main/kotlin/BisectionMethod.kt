class BisectionMethod(var a: Float, var b: Float, val e: Float, val f: (x: Float) -> Float) {
    fun run(): Float {
        while (b - a > e) {
            val c = (a + b) / 2
            if (f(a) * f(c) < 0)
                b = c
            if (f(a) * f(b) < 0)
                a = c
        }
        return (a + b) / 2
    }
}

class RelaxationMethod(var a: Float, var b: Float, val e: Float, val f: (x: Float) -> Float) {
    fun run(): Float {
        
    }
}
