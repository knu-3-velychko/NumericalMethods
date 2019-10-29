//a and b - 2d points on line
//p - 2d point
class KaczmarzMethod(val a: Vector2D, val b: Vector2D, val p: Vector2D, val e: Double) {
    fun getIntersection(): Vector2D? {
        var prevX: Vector2D
        var x = p
        do {
            prevX = x
            x = a + dot(p - a, b - a) / dot(b - a, b - a) * (b - a)
        } while (norm(prevX, x) > e)
        return null
    }

    private fun norm(a: Vector2D, b: Vector2D): Double {
        var result = 0.0
        for (i in 0 until a.size) {
            result += (a[i] - b[i]) * (a[i] - b[i])
        }
        return result
    }
}