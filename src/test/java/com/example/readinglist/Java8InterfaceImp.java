package com.example.readinglist;

/**
 * @Author misaki
 * @Date 2019-02-28 21:34
 */
public class Java8InterfaceImp implements Java8Interface {

    //结果证明java8中允许存在两种在接口中声明却不需要实现的方法
    //default 方法可以被重写 static方法不能被重写，也无法调用
    public static void main(String[] args) {
        new Java8InterfaceImp().f2();
    }

    @Override
    public void f3() {

    }
}
