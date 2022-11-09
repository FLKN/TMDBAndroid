package com.flknlabs.app1.structurals

interface Color {
    fun getColor()
}

class Yellow: Color {
    override fun getColor() {
        println("Yellow")
    }
}

class Red: Color {
    override fun getColor() {
        println("Red")
    }
}

interface Bridge {
    val color: Color
    fun show()
}

class WoodBridge(override val color: Color): Bridge {
    override fun show() {
        print("The wood house color is ")
        color.getColor()
    }
}

class RockBridge(override val color: Color): Bridge {
    override fun show() {
        print("The rock house color is ")
        color.getColor()
    }
}

