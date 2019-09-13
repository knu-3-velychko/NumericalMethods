interface Method {
    val result: Double?

    var iterations: Int?

    var time: Long?
    fun calculate(): Double?
}