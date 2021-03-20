package com.yonyou.kotlin.dali

/**
 *
 *Created by ufsoft on2021/2/15
 *describle:
 */
interface Bird {
    fun fly()
}
class Sparrow : Bird {
    override fun fly() {
        println("Sparrow: is fly.")
        Thread.sleep(1000)
    }
}
// 老鹰
class Eagle : Bird {
    override fun fly() {
        println("Eagle: is fly.")
        Thread.sleep(2000)
    }
}
class BirdProxy(private val bird: Bird) : Bird {
    override fun fly() {
        println("BirdProxy: fly start.")
        val start = System.currentTimeMillis() / 1000
        bird.fly()
        println("BirdProxy: fly end and cost time => ${System.currentTimeMillis() / 1000 - start}s")
    }
}

fun main(args: Array<String>) {
    BirdProxy(Sparrow()).fly()
    println()
    BirdProxy(Eagle()).fly()
}