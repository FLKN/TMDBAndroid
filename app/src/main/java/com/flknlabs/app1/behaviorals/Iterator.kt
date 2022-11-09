package com.flknlabs.app1.behaviorals

interface Iterator {
    fun hasNext(): Boolean
    fun next(): Product
}

data class Product(private val id: Int, private val name: String) {
    override fun toString() = "Product: {id= $id, name='$name'}"
}

class ProductListCollection {
    private val products = ArrayList<Product>()

    fun add(product: Product) { products.add(product) }

    fun createIterator() = ListIterator(this)

    inner class ListIterator(private val collection: ProductListCollection) : Iterator {
        private var index = 0
        override fun next(): Product {
            return collection.products[index++]
        }

        override fun hasNext(): Boolean {
            return index < collection.products.size
        }
    }
}