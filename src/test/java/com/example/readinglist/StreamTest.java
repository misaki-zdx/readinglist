package com.example.readinglist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * java8 lamdba+stream 初体验
 * <p>
 * Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。
 * <p>
 * Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
 * <p>
 * Stream API可以极大提高Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。
 * <p>
 * 这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。
 * <p>
 * 元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。
 * <p>
 * <p>
 * <p>
 * 什么是 Stream？
 * Stream（流）是一个来自数据源的元素队列并支持聚合操作
 * <p>
 * 元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。
 * 数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
 * 聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
 * 和以前的Collection操作不同， Stream操作还有两个基础的特征：
 * <p>
 * Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。
 * 内部迭代： 以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。 Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现。
 * <p>
 * <p>
 * <p>
 * 生成流
 * 在 Java 8 中, 集合接口有两个方法来生成流：
 * <p>
 * stream() − 为集合创建串行流。
 * <p>
 * parallelStream() − 为集合创建并行流。
 *
 * @Author misaki
 * @Date 2019-02-28 22:17
 */
public class StreamTest {

    @Test
    public void f1() {
        int[] ints = {1, 2, 3};
        //int为基本类型 而基本类型不能为范型类，所以这里直接存为一个int[]数组类
        List<int[]> ints1 = Arrays.asList(ints);
        System.out.println(Arrays.toString(ints1.get(0)));
    }

    @Test
    public void f2() {
        String[] strs = {"misaki", "xiaomin", "tom"};
        List<String> strings = Arrays.asList(strs);
        //System.out.println(strings+";"+strings.size());
        List<String> friends = new ArrayList<>();
        //优雅而简单 lamdba（虽然读起来会有一点点晦涩）
        strings.stream().map(String::toUpperCase).forEach(name -> System.out.print(name + " "));
    }

    @Test
    public void f3() {
        String[] strs = {"misaki", "xiaomin", "tom"};
        List<String> strings = Arrays.asList(strs);
        List<String> m = strings.stream().filter(name -> name.startsWith("m")).collect(Collectors.toList());
        System.out.println(m);
    }

    @Test
    public void f4() {
        Random random = new Random();
        System.out.println(random.nextInt(10));
        //Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。以下代码片段使用 forEach 输出了10个随机数：
        random.ints(2).limit(5).forEach(System.out::println);
    }

    @Test
    public void f5() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        //map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println(squaresList);
    }

    @Test
    public void f6(){
        //Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：

        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }
}
