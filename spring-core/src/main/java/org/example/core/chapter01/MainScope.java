package org.example.core.chapter01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainScope {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter01/scope.xml");
        Sleep sleepA = context.getBean("sleep1", Sleep.class);
        Sleep sleepB = context.getBean("sleep1", Sleep.class);
        Sleep sleepC = context.getBean("sleep1", Sleep.class);
        Sleep sleepD = context.getBean("sleep2", Sleep.class);
        Sleep sleepE = context.getBean("sleep2", Sleep.class);
        Sleep sleepF = context.getBean("sleep2", Sleep.class);
        System.out.printf("Accessed sleep1 three times, got:[%s, %s, %s]. %n", sleepA, sleepB, sleepC);
        System.out.printf("Accessed sleep2 three times, got:[%s, %s, %s]. %n", sleepD, sleepE, sleepF);
    }
}
