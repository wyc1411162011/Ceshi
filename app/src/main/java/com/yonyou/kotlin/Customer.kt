package com.yonyou.kotlin

/**
 *
 *Created by ufsoft on2021/2/7
 *describle:
 */
class Customer ( name:String = "wyc", age:Int = 30){
    var name:String = "h"
    get() = field;
    set(value) {
        println()
        field = value
    }

    var age:Int =20
    var value:Int
        get() = age;
        set(value)  {
            age = value
        }
    fun dayin(name:String , age: Int =40,shengao:Int=172){
        System.out.println("name 的值$name age的值$age 身高$shengao");
    }
    fun add(a:Int,b:Int):Int = a+b
    fun getSuanfa(){
        fun  process(){
            println("process");
        }
        process()
        println("调用这个方法")

    }

}
public open class Outer{
    var number:Int = 1234;
    internal var internalNumber:Int=34444;
    //没有iner
      class staticNeibulei{
        fun dayin(){

            println("静态内部类的打印");
            //不能访问number
            //println("内部类的打印"+number);
        }
      }
    //普通内部类能访问number
    inner class Neibulei{
        fun dayin(){
            println("内部类的打印"+number);
        }
    }
}
class child:Outer(){

}
var demo = Outer().Neibulei()