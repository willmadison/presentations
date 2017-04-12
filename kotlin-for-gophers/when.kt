fun main(args: Array<String>) {
    print("Kotlin runs on ")

    val os = System.getProperty("os.name")
    when (os) {
        "Linux" -> println("Linux.")
        "Mac OS X" -> println("OS X.")
        else -> println("$os.")
    }
}