package com.flknlabs.app1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.flknlabs.app1.behaviorals.*
import com.flknlabs.app1.creationals.*
import com.flknlabs.app1.structurals.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*println("---Singleton")
        runSingleton()
        println("---Factory")
        runFactory()
        println("---Builder")
        runBuilder()
        println("---Adapter")
        runAdapter()
        println("---Facade")
        runFacade()
        println("---Bridge")
        runBridge()
        println("---Iterator")
        runIterator()
        println("---Memento")
        runMemento()*/
        println("---Observer")
        runObserver()
    }

    private fun runSingleton() {
        Singleton.getInstance("").work()
    }

    private fun runFactory() {
        val normal = Factory.getUser(UserType.Normal, "James", "Smith")
        with(normal) {
            println(getFullName())
            println(status())
            println("Show ads: ${showAds()}")
        }

        val premium = Factory.getUser(UserType.Premium, "Peter", "Brown")
        with(premium) {
            println(getFullName())
            println(status())
            println("Show ads: ${showAds()}")
        }
    }

    private fun runBuilder() {
        val dog = Builder()
            .withAge(Age.PUPPY)
            .withWeight(40.0)
            .isSterilized(true)
            .build()

        println("${dog.name}'s diet should be: ${dog.calculateDiet()}")
    }

    private fun runAdapter() {
        val adaptee = AdapteeClass()
        val adapteeData = adaptee.generateData()
        val adapter = Adapter(TargetClass())
        val convertedData = adapter.convertData(adapteeData)

        println("${convertedData.size}")
        println("${convertedData[1].index}")
        println(convertedData[1].data)
    }

    private fun runFacade() {
        val facade = Facade()
        val data = ComplexSystemStoreData("John Doe", "5547174063")
        facade.save(data)
        val dataFound = facade.findFirst()

        println("Found: ${dataFound.name} + ${dataFound.phone}")
    }

    private fun runBridge() {
        val yellowWoodHouse = WoodBridge(color = Yellow())
        yellowWoodHouse.show()
        val yellowRockHouse = RockBridge(color = Yellow())
        yellowRockHouse.show()
        val redWoodHouse = WoodBridge(color = Red())
        redWoodHouse.show()
        val redRockHouse = RockBridge(color = Red())
        redRockHouse.show()
    }

    private fun runIterator() {
        val collection = ProductListCollection()
        collection.add(Product(1, "Product 1"))
        collection.add(Product(2, "Product 2"))
        collection.add(Product(3, "Product 3"))

        val iterator = collection.createIterator()

        while (iterator.hasNext()){
            val currentProduct = iterator.next()
            println(currentProduct.toString())
        }
    }

    private fun runMemento() {
        val savedTimes: MutableList<Memento> = ArrayList()
        val life = Life()

        life.set("1000 B.C.")
        savedTimes.add(life.saveToMemento())
        life.set("1000 A.D.")
        savedTimes.add(life.saveToMemento())
        life.set("2000 A.D.")
        savedTimes.add(life.saveToMemento())
        life.set("4000 A.D.")
        life.restoreFromMemento(savedTimes[0])
    }

    private fun runObserver() {
        val observable = Observable(Observer())
        observable.text = "Hello"
        observable.text = "There"
    }
}

