package com.flknlabs.app1.structurals

// 3rd party library
data class TargetData(val index: Float, val data: String)
class TargetClass {
    fun displayData(data: TargetData) {
        println("Data is displayed: ${data.index} - ${data.data}")
    }
}

// Local module
data class AdapteeData(val position: Int, val amount: Int)
class AdapteeClass {
    fun generateData(): List<AdapteeData> {
        val list = arrayListOf<AdapteeData>()
        list.add(AdapteeData(2, 2))
        list.add(AdapteeData(3, 7))
        list.add(AdapteeData(4, 23))
        return list
    }
}

// Adapter definition
interface Converter {
    fun convertData(data: List<AdapteeData>): List<TargetData>
}
class Adapter(private val display: TargetClass): Converter {
    override fun convertData(data: List<AdapteeData>): List<TargetData> {
        val returnList = arrayListOf<TargetData>()
        for (datum in data) {
            val ddt = TargetData(datum.position.toFloat(), datum.amount.toString())
            display.displayData(ddt)
            returnList.add(ddt)
        }
        return returnList
    }
}