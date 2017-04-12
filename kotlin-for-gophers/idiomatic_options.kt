data class Server(private val address: Int = 8080, val connectTimeout: Int = 30000, 
val readTimeout: Int = 30000, val maxConnections: Int = 100) {
    private val listener = ServerSocket(address) 
}

fun main(args: Array<String>) {
    val customPort = Server(9000)
    val customTimeouts = Server(connectTimeout = 15000, readTimeout = 15000)
    val customMaxConns = Server(maxConnections = 300)
}