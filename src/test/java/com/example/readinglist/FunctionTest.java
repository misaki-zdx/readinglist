package com.example.readinglist;

import org.junit.Test;

import java.util.function.Function;

/**
 * 函数式编程 初体验
 */
public class FunctionTest {
    @Test
    public void test1(){
        Function<Integer,Integer> function1 = i-> i+1;
        Integer apply = function1.apply(5);
        System.out.println(apply);
    }
    @Test
    public void test2(){
        Function<Integer,Integer> A=i->i+1;
        Function<Integer,Integer> B=i->i*i;
        Function<Integer, Integer> compose = B.compose(A);
        //B.compose(A).cpmpose(A).andThen(A).apply(5);
    }
}
