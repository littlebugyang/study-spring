package org.example.core.chapter01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainContainer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter01/container.xml");
        context.getBean("demoBeanID", DemoBean.class).speak();
    }
}
