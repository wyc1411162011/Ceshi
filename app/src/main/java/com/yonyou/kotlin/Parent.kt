package com.yonyou.kotlin

import android.util.Log

/**
 *
 *Created by ufsoft on2021/2/7
 *describle:
 */
//接口类似于java中的抽象类并不等同于java接口可以有实现的方法
//接口默认就是open的可以继承
interface MyInterface {
    fun getName():String{
        Log.e("tag","hahahhah")
        return "MyInterface"
    }
    fun process()

}
class MyChildInterface :MyInterface{
    override fun process() {
        TODO("Not yet implemented")
    }

    override fun getName(): String {
        return super.getName()
    }

}
abstract class Derived{
    abstract fun dayin();
    open fun fagnfa(){
        println("Derived的方法")
    }
}
class ChildDericed: Derived() {
    override fun dayin() {
        TODO("Not yet implemented")
    }
    override fun fagnfa(){
        println("ChildDericed的方法")
    }
}
enum class Direction private constructor(var value:Int){
    NORTH(1),SOUTH(2),WEAST(3),EAST(43);

    override fun toString(): String {
        return (value.toString()+"")
    }

}
open class Parent(var vaule1:Int ,var vaule2:Int){
    fun add():Int{
        return vaule1 +vaule2;
    }

}

open class Child(vaule1:Int ,vaule2:Int):Parent(vaule1,vaule2){
    fun sub():Int{
        return vaule1- vaule2;
    }
}
 fun Parent.printReuslt(){
    println("${vaule1}+${vaule2}=${add()}")
}
fun Child.printReuslt(){
    println("$vaule1-$vaule2=${sub()}")
}
class MyClass{
    var mValue:Int =0
    var helle:String="hello"
        get() = field;
        set(value) {
            field = value
        }
    var str:String="1234"
        get() = field;
        set(value) {
            field = value
        }
}
var MyClass.age:Int
get() = 12367;
set(value) {
    println("年龄")
}
class OuterClass{
    companion object CompanionObjectName{
        var name =" 伴生对象"
        fun companionFun(){
            println("调用伴生对象方法");
        }
    }
}
fun OuterClass.CompanionObjectName.test(){
    println("伴生对象的test")
}