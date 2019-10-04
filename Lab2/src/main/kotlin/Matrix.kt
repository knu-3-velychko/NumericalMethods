

class Matrix(val matrix: Array<Array<Double>>) {
    operator fun get(i: Int, j: Int): Double {
        if (i >= matrix.size)
            throw IndexOutOfBoundsException("Invalid i coordinate $i")
        if (j >= matrix.size)
            throw IndexOutOfBoundsException("Invalid j coordinate $j")
        return matrix[i][j]
    }

    operator fun set(i: Int, j: Int, value: Double) {
        if (i >= matrix.size)
            throw IndexOutOfBoundsException("Invalid i coordinate $i")
        if (j >= matrix.size)
            throw IndexOutOfBoundsException("Invalid j coordinate $j")
        matrix[i][j] = value
    }
}