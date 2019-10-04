import java.io.File

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val file = File("1.txt")
            file.createNewFile()
            println("Million hugs <3")
        }
    }
}