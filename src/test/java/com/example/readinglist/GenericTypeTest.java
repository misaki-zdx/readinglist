package com.example.readinglist;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型类学习
 * @author Misaki
 * @date 2019/2/28/028 13:46
 **/
public class GenericTypeTest {

    /**
     * 通过下面的例子可以证明，在编译之后程序会采取去泛型化的措施。也就是说Java中的泛型，只在编译阶段有效。在编译过程中，正确检验泛型结果后，会将泛型的相关信息擦出，并且在对象进入和离开方法的边界处添加类型检查和类型转换的方法。也就是说，泛型信息不会进入到运行时阶段。
     *
     * 对此总结成一句话：泛型类型在逻辑上看以看成是多个不同的类型，实际上都是相同的基本类型。
     */
    @Test
    public void f1(){
        List<String> stringArrayList = new ArrayList<>();
        List<Integer> integerArrayList = new ArrayList<>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if(classStringArrayList.equals(classIntegerArrayList)){
            System.out.println("泛型测试,类型相同");
        }
    }

    public void showKeyValue(Generic<?> obj){
        System.out.println("泛型测试key value is " + obj.getKey());
    }


    /**
     * 类型通配符一般是使用？代替具体的类型实参，注意了，此处’？’是类型实参，而不是类型形参 。重要说三遍！此处’？’是类型实参，而不是类型形参 ！ 此处’？’是类型实参，而不是类型形参 ！
     * 再直白点的意思就是，此处的？和Number、String、Integer一样都是一种实际的类型，可以把？看成所有类型的父类。是一种真实的类型。
     *
     * 可以解决当具体类型不确定的时候，这个通配符就是 ?  ；当操作类型时，不需要使用类型的具体功能时，只使用Object类中的功能。那么可以用 ? 通配符来表未知类型。
     */
    @Test
    public void f2(){
        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);
        showKeyValue(gInteger);
    }

    @Test
    public void f3(){
        List<?>[] ls = new ArrayList<?>[10];
        ls[0] = new ArrayList<String>();
        ls[1] = new ArrayList<Integer>();
    }

    @Test
    public void f4(){
        List<?>[] lsa = new List<?>[10]; // Not really allowed.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Unsound, but passes run time store check
        Integer i = (Integer) lsa[1].get(0);
    }

    /**
     * 其他笔记:
     * 静态方法有一种情况需要注意一下，那就是在类中的静态方法使用泛型：静态方法无法访问类上定义的泛型；
     * 如果静态方法操作的引用数据类型不确定的时候，必须要将泛型定义在方法上。
     *
     *
     * 泛型类，是在实例化类的时候指明泛型的具体类型；泛型方法，是在调用方法的时候指明泛型的具体类型。
     */

}
