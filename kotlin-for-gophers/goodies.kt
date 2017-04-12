// START INFERREDRETURN OMIT
fun addition(x: Int, y: Int) = x + y
// END INFERREDRETURN OMIT

// START IFASEXPRESSION OMIT
fun max(a: Int, b: Int) = if (a > b) a else b 
// END IFASEXPRESSION OMIT

// START SMARTCAST OMIT
open class Person(open val name: String = "")

data class Gopher(override val name: String = "", val knowsGo: Boolean = true) : Person(name)

fun viewPerson(p: Person) {
    println(p.knowsGo) // Compile time error
    println("${p.name} is a person.")

    if (p is Gopher) {
        val knowsGo = if (p.knowsGo) "knows Go!" else "doesn't know Go :(" // Works just fine!
        println("${p.name} $knowsGo")
    } else {
        println("Indeed ${p.name} is JUST a person.")
    }
}

fun main(args: Array<String>) {
    viewPerson(Gopher("Will"))
    viewPerson(Person("Non Gopher Will"))
}
// END SMARTCAST OMIT

// END SMARTCAST OMIT

// START EXTENSIONFUN OMIT

fun String.toggleCase() = 
    this.map { 
        if (it.isUpperCase()) it.toLowerCase() else it.toUpperCase() 
    }.joinToString("")

fun main(args: Array<String>) {
   println("Will".toggleCase() == "wILL") // true
}

// END EXTENSIONFUN OMIT

// START FUNCTIONALPRIMS OMIT

fun main(args: Array<String>) {
    val values = 1..10

    val evens = values.filter { it % 2 == 0 }
    println(evens) // [2, 4, 6, 8, 10]

    val groups = values.groupBy { if (it % 2 == 0) "even" else "odd"}
    println(groups) // {odd=[1, 3, 5, 7, 9], even=[2, 4, 6, 8, 10]}

    val tens = values.map { it * 10 }
    println(tens) // [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]
}

// END FUNCTIONALPRIMS OMIT