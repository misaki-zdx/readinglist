package com.example.readinglist;

import org.junit.Test;

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
}
