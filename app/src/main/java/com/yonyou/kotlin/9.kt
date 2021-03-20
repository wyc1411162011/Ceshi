package com.yonyou.java.nine

/**
 *
 *Created by ufsoft on2021/2/13
 *describle:
 */
infix fun String.div(str:String):String{
   return this.replace(str,"")
}
fun <T>asList(vararg ts:T,value1:Int,value2:Int):List<T>{
    var list = ArrayList<T>()
    for(t in ts){
        list.add(t)
    }
    println("value1的值${value1} value2的值${value2}")
    return list
}
fun main(args: Array<String>) {
    var str:String ="wyc1234"
    println(str div "1234" div "w")
    var list= asList(1,2,"3","4",value1 = 8,value2 = 9)
    println(list)
    var a = arrayOf(1,2,3,19)
    var list1 = asList(*a,value2 = 8,value1 = 22)
    println(list1)


}