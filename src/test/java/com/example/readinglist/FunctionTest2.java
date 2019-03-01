package com.example.readinglist;

import org.junit.Test;


/**
 * @author Misaki
 * @date 2019/2/28/028 15:14
 **/
public class FunctionTest2 {

    @Test
    public void f1(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };

        Runnable runnable1 = ()-> System.out.println("xxxx");

        //so 实际是对象的创建
    }
}
