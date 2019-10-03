import java.io.File

class MatricReader(val fName: String) : {
    fun read(): Matrix {
        File(fName).forEachLine {
            it.split(" ")
        }
        return Matrix();
    }
}
