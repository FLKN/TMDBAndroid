package com.flknlabs.app1.structurals

data class ComplexSystemStoreData(val name: String, val phone: String)
class ComplexSystemStore {
    companion object {
        val NAME_KEY = "NAME_KEY"
        val PHONE_KEY = "PHONE_KEY"
    }

    private var name: String? = null
    private var phone: String? = null

    fun store(key: String, value: String) {
        if (key == NAME_KEY) name = value
        if (key == PHONE_KEY) phone = value
    }
    fun readName() = name
    fun readPhone() = phone

    /*private val filePath = "/data/default.prefs"
    private val cache: HashMap<String, String>
    init {
        println("Reading data from the file: $filePath")
        cache = HashMap()
        // Read properties from file and put to cache
    }
    fun store(key: String, value: String) {
        cache[key] = value
    }
    fun read(key: String) = cache[key] ?: ""
    fun commit() = println("Storing cached data to file $filePath")*/
}

// Facade
class Facade {
    private val systemPreferences = ComplexSystemStore()
    fun save(user: ComplexSystemStoreData) {
        systemPreferences.store(ComplexSystemStore.NAME_KEY, user.name)
        systemPreferences.store(ComplexSystemStore.PHONE_KEY, user.phone)
    }

    fun findFirst() = ComplexSystemStoreData(
        systemPreferences.readName() ?: "",
        systemPreferences.readPhone() ?: ""
    )

    /*fun save(user: ComplexSystemStoreData) {
        systemPreferences.store("NAME_KEY", user.name)
        systemPreferences.store("PHONE_KEY", user.phone)
        systemPreferences.commit()
    }
    fun findFirst() = ComplexSystemStoreData(
        systemPreferences.read("NAME_KEY"),
        systemPreferences.read("PHONE_KEY")
    )*/
}