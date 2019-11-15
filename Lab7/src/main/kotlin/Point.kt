data class Point(var x: Double, var y: Double)

fun Array<Point>.split(): Pair<DoubleArray, DoubleArray> {
    val xArray = DoubleArray(this.size) { 0.0 }
    val yArray = DoubleArray(this.size) { 0.0 }

    for (i in this.indices) {
        xArray[i] = this[i].x
        yArray[i] = this[i].y
    }

    return xArray to yArray
}