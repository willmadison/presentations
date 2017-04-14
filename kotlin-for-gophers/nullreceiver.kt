data class Node(val value: Int, val left: Node? = null, val right: Node? = null)

fun Node?.Sum(): Int = when(this) {
    null -> 0
    else -> this.value + this.left.Sum() + this.right.Sum()
}

fun main(args: Array<String>) {
    val tree = Node(1, right = Node(9))
    println(tree.Sum()) // 10
}