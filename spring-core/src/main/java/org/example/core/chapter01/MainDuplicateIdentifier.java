package org.example.core.chapter01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainDuplicateIdentifier {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter01/duplicate.identifier.xml");
        System.out.println("I gotta sleep for " + context.getBean("sleep1", Sleep.class).time);
        System.out.println("I gotta sleep for " + context.getBean("sleep2", Sleep.class).time);
        System.out.println("I gotta sleep for " + context.getBean("sleep3", Sleep.class).time);
    }
}
