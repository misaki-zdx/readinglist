package com.example.readinglist;

import java.util.Objects;

/**
 * Function 接口复制测试
 *
 * @author Misaki
 * @date 2019/3/1/001 9:09
 **/
public interface FunctionClon<T,R> {
    R apply(T t);

    default <V> FunctionClon<V,R> compose(FunctionClon<? super V,? extends T> before){
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }
}
