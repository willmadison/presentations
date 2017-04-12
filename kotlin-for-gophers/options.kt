data class Server(private val address: Int = 0, val connectTimeout: Int = 0, 
val readTimeout: Int = 0, val maxConnections: Int = 0) {
    private val listener = ServerSocket(address) 
}

fun NewServer(address: Int, vararg options: (Server) -> Server): Server {
    var server = Server(address)

    for (option in options) {
        server = option(server)
    }

   return server
}