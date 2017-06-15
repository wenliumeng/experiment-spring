package com.everingthing.beginningspring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 在@Component注解之外扩展Bean扫描机制
 * Created by seymour on 2017/6/15.
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("/applicationContext.xml");
        boolean containsFoo = applicationContext.containsBean("foo");
        System.out.println(containsFoo);
    }
}

