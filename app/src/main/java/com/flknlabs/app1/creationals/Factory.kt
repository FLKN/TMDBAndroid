package com.flknlabs.app1.creationals

enum class UserType { Normal, Premium }

object Factory {
    fun getUser(userType: UserType, name: String, surname: String): User {
        return when (userType) {
            UserType.Normal -> Normal(name = name, surname = surname)
            UserType.Premium -> Premium(name = name, surname = surname)
        }
    }
}

interface User {
    val name: String
    val surname: String
    fun getFullName() = "$name $surname"
    fun status(): String
    fun showAds(): Boolean
}

class Normal(override val name: String, override val surname: String) : User {
    override fun status() = "Normal"
    override fun showAds() = true
}

class Premium(override val name: String, override val surname: String) : User {
    override fun status() = "Premium"
    override fun showAds() = false
}