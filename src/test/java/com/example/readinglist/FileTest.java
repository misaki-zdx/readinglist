package com.example.readinglist;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Misaki
 * @date 2019/2/22/022 17:14
 **/
public class FileTest {

    @Test
    public void printCurrentCatalogue(){
        //获取当前工作目录
        String property = System.getProperty("user.dir");
        System.out.println(property);
    }

    @Test
    public void splitTest(){
        String str = ",x,y,z,ab,ad";
        String[] split = str.split(",");
        System.out.println(Arrays.toString(split));
    }
}
