class RelaxationMethod(
    var a: Float,
    var b: Float,
    val e: Float,
    val f: (x: Float) -> Float,
    val derivative: (x: Float) -> Float
) {
    fun run() {
        var x0=(a+b)/2
    }
}