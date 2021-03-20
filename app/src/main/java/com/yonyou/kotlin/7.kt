package com.yonyou.kotlin.seven

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 *
 *Created by ufsoft on2021/2/10
 *describle:
 */
open class MyClass(name:String){
    open var name =name
    open fun verify(){
        println(this.toString()+" "+"verify");
    }
    private fun foo()= object{
        var name:String = "private匿名内部类"
    }
    fun publicFoo()=object {
        var name:String ="public的匿名内部类"
    }
    fun bar(){
        foo().name
    }
    companion object {
        fun create(){
            println("伴生对象的create")
        }
    }
}
fun process(obj:MyClass){
    obj.verify()
    if(obj is MyInterface){
        obj.closeData()
    }
}
interface MyInterface{
    fun closeData(){
        println("closedata的数据")
    }
}
fun main(args: Array<String>) {
//    var n:Int =10
//    process(object :MyClass("wyc"),MyInterface{
//        override fun verify() {
//            n=123;
//            super.verify()
//            println("当前的n"+n)
//        }
//    })
//    var myInterface:MyInterface = object : MyInterface,MyClass("") {
//        override fun closeData() {
//
//        }
//    }
//    foo()
//    MyClass.create()
//    MyClass.Companion.create()
//    var baseImpl:BaseImpl = BaseImpl()
//    var derive:Derive = Derive(baseImpl)
//    derive.print()
//    derive.getName()
//    var c1 = MyClass1()
//    var c2 = MyClass2()
//    c1.name12="Bill"
//    c2.name="Mike"
//    println(c1.name12)
//    println(c2.name)

    //委托属性
//    var  c11 = MyClass1()
//    println(c11.name12)
//    c11.name12="hh"
//    var  c22 = MyClass2()
//    c22.name="1"
//    println(c22.name)
//    println(lazyName)
//    println(lazyName)
//    println(lazyName)
//    var user:User = User();
//    user.name="123"
//    user.name="345"
//    user.name="5676"
//    println(user.name)
    val map = mapOf("name" to "tom",
            "age" to 20
    )
    val mapUser = MapUser(map)
    println(mapUser.name)
    println(mapUser.age)
}
fun foo(){
    //不想有任何对象的标识
    var adHoc = object {
        var x:Int =1;
        var y:Int =2;
    }
    println("对象的标识"+(adHoc.x+adHoc.y));
}
//委托，继承父类的方法可以通过一个其他的对象来实现
interface Base{
    fun print()
}
class BaseImpl:Base{
    override fun print() {
        println("BaseImpl的打印方法")
    }

}
class Derive(b:Base):Base by b{
    fun getName(){
        println("Device的获取名字的方法")

    }
}
class MyClass1{
    var name12:String by Delegate()

}
class MyClass2 {
    var name: String by Mydelegate {
        println("sa的方法")
    }
}
class Delegate{
    var name:String = "Deletgate的名字"
    operator fun getValue(myClass1: MyClass1,property: KProperty<*>):String{
        println("Deltegate的getValue()的方法")
        return this.name
    }
    operator fun setValue(myClass1: MyClass1,property: KProperty<*>,value: String){
        println("Deltegate的setValue()的方法")
        this.name = value
    }
    var xing:Int =1

}
class Mydelegate<T>(sa: ()-> T) {
    var name: String = ""
    var classname =sa()
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        println("$classname Mydelegate get被调用")
        return name
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$classname Mydelegate  set被调用")
        name = value
    }
}
val lazyName:String by lazy {
    println("fdsk")
    "hello"
}
class User{
    var name:String by Delegates.observable("初始值"){
        property, oldValue, newValue ->
        println("旧值：$oldValue 新值 $newValue")
    }
}
class MapUser(var map:Map<String,Any>){
    val name:String by map;
    val age:Int by map;

}