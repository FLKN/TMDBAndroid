package com.flknlabs.app1.behaviorals

class Memento(val savedTime: String?)

class Life {
    private var time: String? = null
    fun set(time: String) {
        println("Setting time to $time")
        this.time = time
    }

    fun saveToMemento(): Memento {
        println("Saving time to Memento")
        return Memento(time)
    }

    fun restoreFromMemento(memento: Memento) {
        time = memento.savedTime
        println("Time restored from Memento: $time")
    }
}
