operator fun DoubleArray.times(array: DoubleArray): DoubleArray {
    val result = DoubleArray(this.size + array.size - 1) { 0.0 }
    for (i in this.indices) {
        for (j in array.indices)
            result[i + j] += this[i] * array[j]
    }
    return result
}

operator fun DoubleArray.times(value: Double): DoubleArray {
    val result = DoubleArray(this.size) { 0.0 }
    for (i in this.indices) {
        result[i] = this[i] * value
    }
    return result
}