package com.yonyou.kotlin

/**
 *
 *Created by ufsoft on2021/2/7
 *describle:
 */
class Teacher{
    fun teach(){
        println("老师在教育学生")
    }
}
open class Student{
    fun listen(){
        println("学生正在听老师讲课");
    }
    open fun Teacher.jiaoliu(){
        teach()
        this@Student.teach()
        println("老师正在交流");
    }
    fun clallTeacher( teacher:Teacher){
        teacher.jiaoliu()
    }
    fun teach(){
        println("自己反省")
    }
}
class StudentErdai:Student(){
    override fun Teacher.jiaoliu(){
        println("学生二代在扩展继承  老师的交流")
    }
}
data class User(var name:String,var age:Int){
//    var name:String = name;
//    var age:Int = age;
//    override fun toString(): String {
//       return "name是${name}age是${age}"
//    }
//
//    override fun equals(other: Any?): Boolean {
//       if(other is User){
//            if(other.name.equals(this.name) && other.age == age){
//                return true
//            }
//       }
//        return false
//    }
}