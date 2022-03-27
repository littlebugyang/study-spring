# 第 3 章

上一章讲解了如何在配置文件中描述 Beans 的依赖关系。这章介绍如何使用注解来简化这一注入过程。

## 参考资料
- https://www.bilibili.com/video/BV1WE411d7Dv
- https://docs.spring.io/spring-framework/docs/5.3.17/reference/html/core.html

## 使用前需知

当注解作用和 XML 作用重合时，后者优先级更高。参考官方文档的解释：
> Annotation injection is performed before XML injection. Thus, the XML configuration overrides the annotations for properties wired through both approaches.

两者并无优劣之分，各有优缺点。

即使是使用注解，也需要在配置文件中加上 `<context:annotation-config />`，以激活 Spring 关于注解的功能。

还要添加 namespace 和 schema location，最终结果为：

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>
    <!-- ... -->
</beans>
```

## @Autowired

在 `MainAnnotationAutowired.java` 中，类对 Woman 的依赖，是通过对域变量 woman 应用 @Autowired 注解实现的：
```java
@Autowired
private Woman woman;
```

该注解通过类型找 Bean：
> Make sure that your target components are consistently declared by the type that you use for your @Autowired-annotated injection points. Otherwise, injection may fail due to a "no type match found" error at runtime. 

由于 XML 中只存在 1 个 Woman 类，所以自动装配成功。

但经过测试，如果 id/name 与变量名相符的话，也是可以装配成功的。

该例子中依赖 Pen 也是同样的方法，但是定义中的 Pen 类有 2 个，IoC 容器无法确定使用哪个 Bean，此时 @Qualifier 就派上用场了。我们可在 @Qualifier 内指定要装配的 Bean 的标识符：

```java
@Autowired
@Qualifier("hero")
private Pen pen;
```

不使用 @Qualifier 的话，也可以把 Bean 们装配到一个数组上：

```java
@Autowired
private Letter[] paragraphs;
```

除了在域变量中使用，我们还可以在构造函数中使用该注解，可指定 IoC 容器生成实例时使用的构造函数：

```java
public AnnotationAutowired(){
    System.out.println("Default constructor invoked. ");
}

@Autowired
public AnnotationAutowired(@Qualifier("para1") Letter letter){
    System.out.println("Parameterized constructor invoked. ");
    this.paragraph1 = letter;
}
```

使用 @Autowired 后，Spring 还会去配置中找跟构造函数参数类型相同的 Bean。

另外，@Autowired 还可以应用在方法上，方法名甚至不需要是 setXXXX：
```java
@Autowired
public void whateverParagraph2(@Qualifier("para2") Letter letter) {
    this.paragraph2 = letter;
}
```

## @Resource

JDK 17 已经没有这个注解了。

@Autowired 是 Spring Framework 提供的注解。Java 也提供了如 @Resource 这样的注解，可被 Spring 识别并操作。详见 `MainAnnotationResource.java`。

在许多场景下 @Resource 与 @Autowired 起同样效果，但是它们之间也存在区别：比如，@Resource 不可应用于构造函数。