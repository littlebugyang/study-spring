package org.example.core.chapter02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainConstructorSetterInjection {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter02/constructor.setter.injection.xml");
        context.getBean("mike", Human.class).call();
    }
}
