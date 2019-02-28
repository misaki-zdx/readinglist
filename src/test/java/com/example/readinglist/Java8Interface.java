package com.example.readinglist;

/**
 *
 * 接口是设计模式中一种 开闭原则 的体验，而java8赋予了接口新的特性，使得接口使用起来更加的得心应手了，这也有助于我们更加内聚自己的代码结构了。
 * 比如collection即可类和接口，排序接口，把很多工具方法都放到接口里了。
 *
 * 在jdk8之后，接口里面咱们都可以写具体的方法了，但这方法比较特殊，只能是静态方法或者默认方法。
 * 这又让我们有更好的设计，可以设计出更加高内聚的代码，更加方便的管理封装。
 *
 *
 * 为什么java的接口里的属性必须是static的？并且要求必须是final的呢？这个留给大家自己做思考。。。算了，顺便奉上吧：
 *
 * 接口中的数据对所有实现类只有一份,所以是static
 * 要使实现类为了向上转型成功,所以必须是final的（接口不能被实例化，所以接口里面如果是变量的话不会被赋初始值这样就会出问题，所以必须是final的。
 * 其实还是为了安全考虑的） 这样接口也能起到一定的模版的作用。
 *
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 *
 * java自带的反编译工具 javap -p class文件
 *
 * @Author misaki
 * @Date 2019-02-28 21:31
 */
public interface Java8Interface {
    //静态方法只能public修饰，默认即为public
    static void f1(){
        System.out.println("this is java8 interface static function");
    }

    default void f2(){
        f1();
        System.out.println("this is java8 interface default function");
    }
    void f3();
}
