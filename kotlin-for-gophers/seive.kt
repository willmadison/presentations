fun generate(context: CoroutineContext) = produce<Int>(context) {
    var start = 2
    while (true) send(start++)
}

fun filter(context: CoroutineContext, input: ReceiveChannel<Int>, divisor: Int) = produce<Int>(context) {
    for (number in input) {
        if (number % divisor != 0) {
            send(number)
        }
    }
}

fun main(args: Array<String>) = runBlocking {
    var current = generate(context)
    for (number in 1..10) {
        val prime = current.receive()
        println(prime) // prints 2, 3, 5, 7, 9, 11...
        current = filter(context, current, prime)
    }
}