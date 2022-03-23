# 第 1 章

## 参考资料
- https://www.bilibili.com/video/BV1WE411d7Dv
- https://docs.spring.io/spring-framework/docs/5.3.17/reference/html/core.html

## 基本概念

### Bean
根据[官方文档](https://docs.spring.io/spring-framework/docs/5.3.17/reference/html/core.html#beans-introduction)：

> A bean is an object that is instantiated, assembled, and managed by a Spring IoC container. 

Java Bean 和 Spring Bean，这两者是有区别的。在学习 Spring 的时候，把 Bean 等价于“Spring 帮我们生成好的对象”好像并没有什么问题。

### Spring IoC container

该容器包办了实现控制反转的大部分琐事，开发者只需要向其提供 metadata。

metadata 的形式可以是 XML 文件、注解、甚至是 Java 代码。

本章主要介绍 XML 类别的 metadata。

## 使用 IoC 容器

我们在 resources 文件夹下创建一个 chapter01.xml 文件，描述 Bean，也就是向容器提供元数据。

在 main 方法中，使用 spring-context，进行上下文的初始化。我们把刚创建的文件名作为参数传入，即可得到一个以此为元数据的 IoC 容器：
```java
public class MainContainer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter01.xml");
        context.getBean("demoBeanID", DemoBean.class).speak();
    }
}
```
而后，我们就可以用这个上下文进行对象的获取了。