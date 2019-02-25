package java8;


import java.util.ArrayList;
import java.util.List;

/**
 * 方法引用
 *
 * @author Misaki
 * @date 2019/2/25/025 10:55
 **/

public class MethodQuote {
    /**
     *
     *不能违反基本原则，无法通过方法引用来获取private修饰的值
     */
    private int x = 4;

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);
    }

    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static MethodQuote create(final Supplier<MethodQuote> supplier) {
        return supplier.get();
    }

    public static void collide(final MethodQuote MethodQuote) {
        System.out.println("Collided " + MethodQuote.toString());
    }

    public void follow(final MethodQuote another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
    public void temp(){
        System.out.println("temp function");
    }

    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }
}
