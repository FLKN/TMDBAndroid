package com.flknlabs.app1.creationals

enum class Age { PUPPY, YOUNGSTER, ADULT, OLD }

class Dog(
    val name: String,
    val age: Age,
    val weight: Double,
    val isSterilized: Boolean,
) {
    fun calculateDiet(): String {
        return when(age) {
            Age.PUPPY -> "Croquettes"
            Age.YOUNGSTER -> "Meat and croquettes"
            Age.ADULT -> "Meat and water"
            Age.OLD -> "Meat and croquettes with fiber"
        }
    }
}

class Builder {
    private var age: Age = Age.ADULT
    private var weight: Double = 10.0
    private var isSterilized = false

    fun withAge(age: Age): Builder {
        this.age = age
        return this
    }

    fun withWeight(weight: Double): Builder {
        this.weight = weight
        return this
    }

    fun isSterilized(isSterilized: Boolean): Builder {
        this.isSterilized = isSterilized
        return this
    }

    fun build() : Dog {
        return Dog("Logan",
            age,
            weight,
            isSterilized)
    }
}