package sams

import JavaRun

data class Fish (var name: String)

fun fishExamples() {
    val fish = Fish("splashy")  // all lowercase

    fish.run {
        name
    }

    /*
    val fish2 = Fish(name = "splashy").apply {
        name = "sharky"
    }
    println(fish2.name)
    */

/*
    myWith (fish.name) {
        println(capitalize())
    }
 */

    /*
    println(fish.let { it.name.capitalize()}
        .let{it + "fish"}
        .let{it.length}
        .let{it + 31})
     */
    println(fish.let { it.name.capitalize()}
        .let{it + "fish"}
        .let{it.length}
        .let{it + 31})
    println(fish)
}

fun myWith(name: String, block: String.() -> Unit) {
    name.block()
}

/*
fun runExample() {
    val runnable = object: Runnable {
        override fun run() {
            println("I'm a Runnable")
        }
    }
    JavaRun.runNow(runnable)
}
*/

/*
fun runExample() {
    JavaRun.runNow({
        println("Passing a lambda as a Runnable")
    })
}
*/

fun runExample() {
    JavaRun.runNow {
        println("Last parameter is a lambda as a Runnable")
    }
}

fun main () {
    fishExamples()
    runExample()
}
