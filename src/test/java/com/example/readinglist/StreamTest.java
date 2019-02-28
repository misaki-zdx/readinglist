package com.example.readinglist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * java8 lamdba+stream 初体验
 *
 * @Author misaki
 * @Date 2019-02-28 22:17
 */
public class StreamTest {

    @Test
    public void f1(){
        int[] ints = {1,2,3};
        //int为基本类型 而基本类型不能为范型类，所以这里直接存为一个int[]数组类
        List<int[]> ints1 = Arrays.asList(ints);
        System.out.println(Arrays.toString(ints1.get(0)));
    }

    @Test
    public void f2(){
        String[] strs= {"misaki","xiaomin","tom"};
        List<String> strings = Arrays.asList(strs);
        //System.out.println(strings+";"+strings.size());
        List<String> friends = new ArrayList<>();
        //优雅而简单 lamdba（虽然读起来会有一点点晦涩）
        strings.stream().map(String::toUpperCase).forEach(name-> System.out.print(name+" "));
    }

    @Test
    public void f3(){
        String[] strs= {"misaki","xiaomin","tom"};
        List<String> strings = Arrays.asList(strs);
        List<String> m = strings.stream().filter(name -> name.startsWith("m")).collect(Collectors.toList());
        System.out.println(m);
    }
}
