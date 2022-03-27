package org.example.core.chapter04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainConfiguration {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpecificConfig.class);
        System.out.println(context.getBean("macBook", PC.class).model);
        System.out.println(context.getBean("matePad", Tablet.class).model);
        System.out.println(context.getBean("iPad", Tablet.class).model);
    }
}
