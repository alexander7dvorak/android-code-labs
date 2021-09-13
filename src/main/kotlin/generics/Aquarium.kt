package generics

//class Aquarium<T>(val waterSupply: T)
//class Aquarium<T: WaterSupply>(val waterSupply: T)
/*
class Aquarium<T: WaterSupply>(val waterSupply: T) {
    fun addWater() {
        check(!waterSupply.needsProcessing) { "water supply needs processing first" }
        println("adding water from $waterSupply")
    }
}
*/
/*class Aquarium<out T: WaterSupply>(val waterSupply: T) {

}
*/
interface Cleaner<in T: WaterSupply> {
    fun clean(waterSupply: T)
}

class TapWaterCleaner : Cleaner<TapWater> {
    override fun clean(waterSupply: TapWater) =   waterSupply.addChemicalCleaners()
}

class Aquarium<out T: WaterSupply>(val waterSupply: T) {
    fun addWater(cleaner: Cleaner<T>) {
        if (waterSupply.needsProcessing) {
            cleaner.clean(waterSupply)
        }
        println("water added")
    }
    inline fun <reified R: WaterSupply> hasWaterSupplyOfType() = waterSupply is R
}
fun addItemTo(aquarium: Aquarium<WaterSupply>) = println("item added")
/*
fun genericsExample() {
    val aquarium = Aquarium<TapWater>(TapWater())
    println("water needs processing: ${aquarium.waterSupply.needsProcessing}")
    aquarium.waterSupply.addChemicalCleaners()
    println("water needs processing: ${aquarium.waterSupply.needsProcessing}")
}
*/

/*
fun genericsExample() {
    val aquarium2 = Aquarium("string")
    println(aquarium2.waterSupply)
}
*/

/*
fun genericsExample() {
    val aquarium3 = Aquarium(null)
    if (aquarium3.waterSupply == null) {
        println("waterSupply is null")
    }
}
*/

/*
fun genericsExample() {
    val aquarium4 = Aquarium(LakeWater())
    aquarium4.waterSupply.filter()
    aquarium4.addWater()
}
*/

/*
fun genericsExample() {
    val aquarium = Aquarium(TapWater())
    addItemTo(aquarium)
}
*/

/*
fun genericsExample() {
    val cleaner = TapWaterCleaner()
    val aquarium = Aquarium(TapWater())
    aquarium.addWater(cleaner)
}
*/

open class WaterSupply(var needsProcessing: Boolean)

class TapWater : WaterSupply(true) {
    fun addChemicalCleaners() {
        needsProcessing = false
    }
}

class FishStoreWater : WaterSupply(false)

class LakeWater : WaterSupply(true) {
    fun filter() {
        needsProcessing = false
    }
}

fun <T: WaterSupply> isWaterClean(aquarium: Aquarium<T>) {
    println("aquarium water is clean: ${!aquarium.waterSupply.needsProcessing}")
}

/*
fun genericsExample() {
    val aquarium = Aquarium(TapWater())
    isWaterClean(aquarium)
}
*/

/*
fun genericsExample() {
    val aquarium = Aquarium(TapWater())
    println(aquarium.hasWaterSupplyOfType<TapWater>())   // true
}
*/

inline fun <reified T: WaterSupply> WaterSupply.isOfType() = this is T

/*
fun genericsExample() {
    val aquarium = Aquarium(TapWater())
    println(aquarium.waterSupply.isOfType<TapWater>())
}
*/

inline fun <reified R: WaterSupply> Aquarium<*>.hasWaterSupplyOfType() = waterSupply is R

fun genericsExample() {
    val aquarium = Aquarium(TapWater())
    println(aquarium.hasWaterSupplyOfType<TapWater>())
}

fun main() {
    genericsExample()
}