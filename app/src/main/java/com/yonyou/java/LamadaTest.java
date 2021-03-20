package com.yonyou.java;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by ufsoft on2021/2/5
 * describle:
 */
class LamadaTest {
    public static void main(String[] args) {
        //lamada表达式的作用减少创建接口对象的步骤，接口里面只能有一个抽象方法
        Converter converter1 = (from) -> Integer.valueOf(from);
        int value =converter1.convert("99");
        System.out.println("lamada表达式的value "+ value);
        Converter converter2 = Integer::valueOf;
        System.out.println("新型的  "+converter2.convert("12"));
        Converter converter3 = "abc"::indexOf;
        int index= converter3.convert("b");
        System.out.println("当前的index  "+ index);
        MyTest myTest = (name,start,end)->name.substring(start,end);
        String result = myTest.test("012345678",1,3);
        System.out.println("返回的结果  "+result);
        MyTest myTest1 = String::substring;
        System.out.println("返回的结果   "+myTest1.test("起舞费时间第六课",2,4));


        YouTest youTest = (name)->new JFrame(name);
        YouTest test = JFrame::new;
       System.out.println( youTest.add(1,2));
       String []arr1 = new String []{"1","2","3"};


    }
}

