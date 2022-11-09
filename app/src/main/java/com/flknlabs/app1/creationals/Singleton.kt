package com.flknlabs.app1.creationals

class Singleton private constructor(private val name: String) {

    companion object {
        @Volatile
        private lateinit var instance: Singleton

        fun getInstance(name: String): Singleton {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = Singleton(name)
                }
                return instance
            }
        }
    }

    fun work() {
        println("This is Singleton $name class!")
    }
}

/*object Singleton {
    fun work() {
        println("This is Singleton class!")
    }
}*/