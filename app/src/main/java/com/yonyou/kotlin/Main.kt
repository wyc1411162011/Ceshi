package com.yonyou.kotlin

import a.b.Singleton


/**
 *
 *Created by ufsoft on2021/2/5
 *describle:
 */

fun main(args:Array<String>) {
    var user1 = User("wen",12);
    var user2 = User("wen",12);
    println(user1);
    println(user2);
    println(user1.equals(user2))
    var copy = user1.copy();
    println(copy)
    var (m,n) = user1
    println("解构出来的数据 名字:${m}  age ${n}")
}
