package a.b

/**
 *
 *Created by ufsoft on2021/2/5
 *describle:
 */
fun getName():String{
    return "getName的返回值"
}
class MyClass( name:String){
    var  value = name;
    //没有这个初始化方法也可以
    init {
        println(this.javaClass.name +"主构造方法 " + name+ value)
    }
}
fun testIf(){
    //简单的if的句子
    var number:Int;
    var number1 = 1;
    var number2= 3;
    if(number1 >= number2){
        number = number1
    }else{
        number = number2;
    }
    println("当前if的值"+ number)

    //if语句作为表达式
    var a = 10
    var b:Int = 20;
    var c = if (a>=b) a else b
    println("当前的c的值$c");
    //复杂的if语句作为表达式的
    var d = if(a > b){
        println("选择了a "+a);
        a
    }else{
        println("选择了b "+b);
        b
    }
    println("获取复杂的d的值$d");

}
fun testWhen(){
    var n =1;
    when(n){
        1->{
            println("当前是1的情况");
        }
        2->{
            println("当前是2的情况");
        }
    }
    var number =3;
    var result = when(number){
        1->{
            println("when表达式为1的情况");
            1;
        }
        2->{
            println("when表达式为2的情况")
            2
        }
        else ->{
            println("when表达式为其他的情况")
            1234;
        }
    }

    var m =89;
    //when in的情况

    when(m){
        in 1..20->{
            println("when是1-20的情况");
        }
        in 21..40->{
            println("when是21-40的情况")
        }
        !in 41..60->{
            println("when不是41-60");
        }
        else->{
            println("when的其他情况");
        }

    }
    //方法返回值最为参数
    var value = 4;
    when(value){
        chengfa(1)->println("when满足1*1=1");
        chengfa(2)->println("when满足2*2=4");
    }
}
fun chengfa( n:Int):Int{
    return n*n;
}
fun testFor(){
    var arr = intArrayOf(1,2,3)
    for(item in arr){
        println("for的循环"+item);
    }
    for(item:Int in arr){
        println("for的再次循环"+item);
    }
    //索引遍历
    for(i in arr.indices){
        println("arr[$i]的值]"+arr[i]);
    }
    for((index,value) in arr.withIndex()){
        println("for循环的值arr[$index]的值$value");
    }
}
fun testWhile(){
    var n:Int =0;
    while((n++) < 10){
        println("while的打印的值 $n");
    }
}
class Person( name:String){
    var  value = name;
    //没有这个初始化方法也可以
    init {
        println(this.javaClass.name +"主构造方法 " + name+ value)
    }
    constructor(age:Int):this("何其多"){
        println("构造方法的age  $age");
    }
}
class Singleton private constructor(){
    public var value:Singleton ? = null
    private object mHolder {
        val INSTANCE = Singleton();
    }
    companion object {
        fun getInstance():Singleton{
            return mHolder.INSTANCE
        }
    }
}