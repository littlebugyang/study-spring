package org.example.core.chapter04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainComponent {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter04/component.example.xml");
        System.out.println(context.getBean("PC", PC.class).model);
        System.out.println(context.getBean("PC", PC.class));
        System.out.println(context.getBean("PC", PC.class));
    }
}
