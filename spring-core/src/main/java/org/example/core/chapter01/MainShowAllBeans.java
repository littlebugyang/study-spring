package org.example.core.chapter01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class MainShowAllBeans {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter01/show.all.beans.xml");
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    }
}
