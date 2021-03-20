package com.yonyou.kotlin.eight

import kotlin.math.max

/**
 *
 *Created by ufsoft on2021/2/13
 *describle:
 */
fun main(arg: Array<String>) {
    val mobilePhoto = mobilePhoto()
    processProduct(mobilePhoto,::mobliePhoneArea)
    println(mobilePhoto)
    var number=max(1,2,jisuanMax = {
        a,b->
        a+b+1056
    })
    println(number)
    var shuzi=max(1,2, sum)
    println(shuzi)
    var shuzi1=max(21, sum2)
    println(shuzi1)
    println(cesh)
    println(text())
   var textNumber= text(sub={
        it
    })
    println(textNumber)
   var yige= max(1,::yige)
    println("一个函数"+yige)
}
interface product{
    var area:String
    fun sell(name:String)

}
class  mobilePhoto:product{
    override var area: String =""

    override fun sell(name: String) {
        println("卖$name")
    }

    override fun toString(): String {
        return "mobilePhoto(area='$area')"
    }

}

fun mobliePhoneArea(name:String):String{
    return "$name 美国"
}
fun processProduct(product: product,area: (name :String)->String):product{
    product.area=area("iphoto")
    return product
}
fun max(a:Int,b:Int,jisuanMax:(Int,Int)->Int):Int{
    return jisuanMax(a,b)
}
fun max(a:Int,jisuan:(Int)->Int):Int{
    return jisuan(a)
}
fun yige(a:Int):Int{
    println("一个函数的调用")
   return a+34;
}
var sum={
    x:Int,y:Int->
    println("建议的")
    x+y;
}
var sum1 :(Int,Int) -> Int ={ x,y ->x+y}
var sum2:(Int)->Int={x->x}
//匿名函数
var cesh = fun (x:Int,y:Int):Int{
    return x+y;
}
//函数返回值为函数
fun text():(Int)->Int{
    var a=3;
    return fun (a:Int):Int{
        return a+1;
    }
}
fun text(sub:(Int)->Int):Int{
    return sub(1087)
}