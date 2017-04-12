data class Vertex(val lat: Double, val long: Double)

fun main(args: Array<String>) {
    val m = mapOf(
            "Pivotal Labs (San Francisco)" to Vertex(37.7818, -122.4040),
            "The Varsity" to Vertex(37.7818, -122.4040)
    )

    println(m) 
    // {Pivotal Labs (San Francisco)=Vertex(lat=37.7818, long=-122.404), 
    // The Varsity=Vertex(lat=37.7818, long=-122.404)}
}