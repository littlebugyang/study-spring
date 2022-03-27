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

我们在 resources/chapter01 文件夹下创建一个 container.xml 文件，描述 Bean，也就是向容器提供元数据。

在 main 方法中，使用 spring-context，进行上下文的初始化。我们把刚创建的文件名作为参数传入，即可得到一个以此为元数据的 IoC 容器：

```java
public class MainContainer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter01/container.xml");
        context.getBean("demoBeanID", DemoBean.class).speak();
    }
}
```
而后，我们就可以用这个上下文进行对象的获取了。

## 定义 Bean

接下来我们学习更多关于使用 XML 配置文件定义 Bean 的方法。本项目中，我们在程序运行之前就定义好所有的 Bean；我们也可以在程序运行时动态注册 Bean，但是这样做很可能会导致并发异常的发生，Spring 官方也不推荐这样的行为。

### 全限定类名
定义 Bean 需要全限定类名。

### 标识符

容器内的 Bean 都是独一无二的，其标识符可以是我们所设定的 id 或者 name。除了要求 id 不重复、name 不重复外，容器内不允许某 id 和某 name 同时取同一个值。

容器内标识符的唯一性得不到保证时，容器是无法正常启动的，参考 `MainDuplicateIdentifier.java`，哪怕我们 get 的并不是具有重复标识符的 Bean：

```
Exception in thread "main" org.springframework.beans.factory.parsing.BeanDefinitionParsingException: Configuration problem: Bean name 'sleep1' is already used in this <beans> element
Offending resource: class path resource [chapter01/duplicate.identifier.xml]
```

在 `MainShowAllBeans.java` 中，我们可以通过调用 context 的 getBeanDefinitionNames 方法来获取 IoC 容器中的所有 Bean：

```java
public class MainShowAllBeans {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter01/show.all.beans.xml");
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    }
}
```

输出结果为：

```
[org.example.core.chapter01.Sleep#0, sleep1, sleep2, sleep3, org.example.core.chapter01.Sleep#1, org.example.core.chapter01.Sleep#2]
```

可见，对于没有显式设置标识符的 Bean，IoC 会以全限定类名标识之。存在多个同类型 Bean 时，可通过序号区分。

关于 Bean 的命名方法，原则上是“首字母小写的驼峰”。在使用组件扫描特性时，Spring 会给未命名的组件生成标识符，一般是取类名，将首字母转小写。当类名为全大写时，Spring 会有不同的命名规则。

### 作用域

配置 Bean 的时候，我们可以设定其作用域。该属性值默认为 `singleton`，即单例模式。

设置为 `prototype` 时，每次 getBean 都会得到一个新的实例。

演示参考 `MainScope.java`，其输出结果为：

```
Accessed sleep1 three times, got:[org.example.core.chapter01.Sleep@69ea3742, org.example.core.chapter01.Sleep@69ea3742, org.example.core.chapter01.Sleep@69ea3742]. 
Accessed sleep2 three times, got:[org.example.core.chapter01.Sleep@4b952a2d, org.example.core.chapter01.Sleep@3159c4b8, org.example.core.chapter01.Sleep@73846619]. 
```