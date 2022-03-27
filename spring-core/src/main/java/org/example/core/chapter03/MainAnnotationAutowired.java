package org.example.core.chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class MainAnnotationAutowired {
    @Autowired
    private Woman woman;

    @Autowired
    @Qualifier("hero")
    private Pen pen;

    @Autowired
    private Letter[] paragraphs;

    private Letter paragraph1;
    private Letter paragraph2;

    public MainAnnotationAutowired() {
        System.out.println("Default constructor invoked. ");
    }

    @Autowired
    public MainAnnotationAutowired(@Qualifier("para1") Letter letter) {
        System.out.println("Parameterized constructor invoked. ");
        this.paragraph1 = letter;
    }

    @Autowired
    public void whateverParagraph2(@Qualifier("para2") Letter letter) {
        this.paragraph2 = letter;
    }

    public void convey() {
        System.out.printf("I wrote a letter to %s with a %s pen: %s %s%n", woman.getName(), pen.getBrand(), paragraph1.getContent(), paragraph2.getContent());
        System.out.println("All Letter objects injected are: " + Arrays.toString(paragraphs));
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter03/autowired.example.xml");
        context.getBean("annotationAutowired", MainAnnotationAutowired.class).convey();
    }
}
