package org.example.core.chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import javax.annotation.Resource;

public class MainAnnotationResource {
    @Resource
    private Woman woman;

    @Resource
    private Pen pen;

    @Resource
    private Letter[] paragraphs;

    private Letter paragraph1;

    @Autowired
    public void whateverParagraph1(@Qualifier("para1") Letter letter) {
        this.paragraph1 = letter;
    }

    public void convey() {
        System.out.printf("I wrote a letter to %s with a %s pen: %s%n", woman.getName(), pen.getBrand(), paragraph1.getContent());
        System.out.println("All Letter objects injected are: " + Arrays.toString(paragraphs));
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter03/resource.example.xml");
        context.getBean("annotationResource", MainAnnotationResource.class).convey();
    }
}
