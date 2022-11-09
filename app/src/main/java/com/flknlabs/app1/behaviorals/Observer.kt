package com.flknlabs.app1.behaviorals

import kotlin.properties.Delegates

interface ValueChangeListener {
    fun onValueChanged(newValue: String)
}

class Observer: ValueChangeListener {
    override fun onValueChanged(newValue: String) {
        println("Text is changed to: $newValue")
    }
}

class Observable(listener: Observer) {
    var text: String by Delegates.observable(
        initialValue = "",
        onChange = { prop, old, new ->
            listener.onValueChanged(new)
        }
    )
}