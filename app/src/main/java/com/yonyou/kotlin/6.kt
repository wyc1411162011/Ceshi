package com.yonyou.kotlin

/**
 *
 *Created by ufsoft on2021/2/7
 *describle:
 */
class Box<T>(t:T){
    var value = t
}
fun main(args:Array<String>) {
    var strArr:Array<String> = arrayOf("1","2","3")
    var intArr:Array<Any> = arrayOf(1,2,3)
    copy(strArr,intArr)
    singltonList<Int>(1)

}
//类型投射 只能获取from的获取的方法 设置的方法不行了如set方法
fun copy(from:Array<out Any>,to:Array<Any>){
    to.set(1,"2")
    for (i in from.indices){
        to[i] = from[i]
    }
}
abstract class Source<out T>{
    abstract fun nextT():T
}
//out返回值
class AbstractSouce<out T>:Source<T>(){
    override fun nextT(): T {
        TODO("Not yet implemented")
    }

}
fun demo(str:Source<String>){
    var a:AbstractSouce<String> = AbstractSouce()
    var b:AbstractSouce<Any> = a;
    var compareble :Compareble<String> = Compareble()
    var name:String ="hah"
    println(compareble.conpareTo(name));
}
class Compareble<in T>{
    fun conpareTo(other:T):Int{
        println("compareto的方法")
        return 1
    }
}
fun <T>singltonList(item:T){

}
