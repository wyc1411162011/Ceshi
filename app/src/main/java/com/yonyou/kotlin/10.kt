package com.yonyou.kotlin.shi

/**
 *
 *Created by ufsoft on2021/2/13
 *describle:
 */
data class Person(val name:String,var age:Int){

}
data class MyArrayItem(var  key:Int,var value:String,var comment:String)
fun valueArray():Collection<MyArrayItem>{
    var result = arrayListOf<MyArrayItem>(MyArrayItem(1,"w","wwwwwww")
    , MyArrayItem(2,"y","yyyyyyy"), MyArrayItem(3,"c","ccccccc"))
return result
}
open class ParentClass
class ChildClass :ParentClass(){

}
fun main(args: Array<String>) {
//    var(name,age)=Person("王二妞",30)
//    println("name是$name  age是$age");
//    var map = mutableMapOf<Int,String>()
//    map.put(10,"wyc")
//    map.put(20,"wangerniu")
//    for ((key,value) in map){
//        println("key的值$key value的值$value")
//    }
//    for(value in valueArray()){
//        println("key的值${value.key} value的值${value.value}  comment的值${value.comment}")
//    }
//    val numbers:MutableList<Int> = mutableListOf(1,2,3)
//
//    val readOnlyView:List<Int> = numbers
//    println(numbers)
//    numbers.add(4)
//    println(readOnlyView)
//    numbers.clear()
//    println(readOnlyView)
//    val strings = hashSetOf("a","b","c")
//    assert(strings.size==4)
//    hashMapOf<String,Int>()

//    for(i in 1..10){
//        println(i*i)
//    }
//    for(i in 10 downTo 1){
//        println(i*i)
//    }
//    for(i in 3..10 step 1){
//        println(i*i)
//    }

//    for(i in 1 until 10){
//        println(i)
//    }
//    var object1 = 1
//    val  parentClass = ParentClass()
//    val childClass:ChildClass = ChildClass()
//    if(childClass is ParentClass){
//
//    }
//    var y:Any? ="12"
//    val  x:Int? = y as? Int?
//    println(x)
//    var b:A.B = A().B()
//    b.diaoyongNeibu()
//    var number1:Int =1
//    var number2:Int=3
//    if(number1!==number2){
//        println("不相等")
//    }
    var number10:Int=1
    println(number10)
    number10.plus(1)
}
class A{
    var nameA:String ="nameA"
    inner class B{
        var nameB:String = "nameB"
       fun Int.food():String{
           var a = this@A;
           var b =this@B
           b.nameB
           println(a)
           var c =this;

           c.toString()
           return ""
       }
        fun diaoyongNeibu(){
            1.food()
        }
    }
}