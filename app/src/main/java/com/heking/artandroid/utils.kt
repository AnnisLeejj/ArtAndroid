package com.heking.artandroid

class utils {
    internal inner class Person {
        lateinit var name: String
    }

    companion object {

        internal var person: Person? = null

        fun get() {
            if (person == null) {
                return
            }
            val name = person!!.name
        }
    }
}
