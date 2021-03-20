package com.yonyou.kotlin.shiyi

import com.yonyou.entity.Student
import java.lang.Exception
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties

/**
 *
 *Created by ufsoft on2021/2/14
 *describle:
 */
@Target(AnnotationTarget.CLASS,AnnotationTarget.FUNCTION,AnnotationTarget.EXPRESSION,AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.CONSTRUCTOR
        ,AnnotationTarget.PROPERTY_GETTER,AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class MyAnnotationClass{

}
@MyAnnotationClass class Foo @MyAnnotationClass constructor(var number:Int){
    @MyAnnotationClass fun bz():Int{
        return (@MyAnnotationClass 1)
    }
    var name:Int =123
    @MyAnnotationClass get() {
        return field
    }
    @MyAnnotationClass set(value) {
        field =value
    }

}
class Person(var name:String,var age:Int){
    var vName:String = this.name
    get()=field
    set(value) {
        field=value
    }
    fun process1(){
        println("当前的name ${name} 当前的age是${age}")
    }
}
fun main(args: Array<String>) {
//    var a:String = "null"
//    var b:String? ="123"
//    var length:Int
//
//    if(null != b){
//        length=b.length
//    }else{
//        length=-10
//    }
//    println(length)
//    println(b?.length)
//    var list:List<String> = arrayListOf("a","b")
//    for(item in list){
//        item?.let {
//            print(it)
//        }
//    }
//    var n = b?.length ?:-1


//    var name:String?=null
//    var nonInt:Int?=null
//    println("出错之后")
//    var value:Int? = name as? Int
//    println(value)
//    val nullbaleList:List<Int?> = listOf(1,2,null)
//    println(nullbaleList)
//    val list:List<Int> = nullbaleList.filterNotNull()
//    println(list)



//    try{
//        throw Exception("生成的错误的信息")
//    }catch (e:Exception){
//        println(e.message)
//    }finally {
//        println("finally的总结")
//    }
//    var foo:Foo= Foo(1);
    var p =Person::class
//    for(member in p.members){
//        println(member.name+" "+member.returnType)
//    }
//    for(member in p.memberProperties){
//        println("属性的值"+member.name+" "+member.returnType)
//    }
//    for(member in p.memberFunctions){
//        println("方法的值"+member.name+" "+member.returnType)
//    }
//    var student = Student::class.java
//    for(member in student.fields){
//        println(member.name+" "+member.type)
//    }

//    var functionName = Person::process1
//    functionName.invoke(Person("wyc",30))
//    var javaMethod = Person::class.java.getMethod("process1")
//    javaMethod.invoke(Person("wyc",340))
    var person = Person("王胖妞",20)
    var name = Person::vName
    println(name.get(person))
    name.set(person,"wyc")
    println(name.get(person))
    var methodName = Person::class.java.getMethod("getVName")
    var huoquName=methodName.invoke(person)
    println("获取的名称:"+huoquName)
    T().print()
    test()
}
class T{
    fun print(){
        println("当前的类名"+javaClass.simpleName)
    }
}
val test = { println("无参数") }

