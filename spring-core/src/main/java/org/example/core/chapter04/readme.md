# 第 4 章

这章介绍如何使用注解来简化 Bean 的定义过程。

## 参考资料
- https://www.bilibili.com/video/BV1WE411d7Dv
- https://docs.spring.io/spring-framework/docs/5.3.17/reference/html/core.html

## 注解简化定义 Bean 流程

当注解作用和 XML 作用重合时，后者优先级更高。参考官方文档的解释：
> Annotation injection is performed before XML injection. Thus, the XML configuration overrides the annotations for properties wired through both approaches.

两者并无优劣之分，各有优缺点。

即使是使用注解，也需要在配置文件中加上 `<context:annotation-config />`，以激活 Spring 关于注解的功能。

使用 @Component 注解时，还需要激活包扫描功能：`<context:component-scan base-package="some.package.name"/>`。

还要添加 namespace 和 schema location，最终结果为：

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="some.package.name"/>

    <context:annotation-config/>

</beans>
```

定义好 base-package 后，IoC 容器会到该包下寻找应用了 @Component 的类。

## @Component

应用了 @Component 的类会被添加到 IoC 容器中。可用 @Value 注入其简单成员：

```java
@Component
@Scope("prototype")
public class PC {
    @Value("MagicBook")
    public String model;
}
```

@Component 可以与 @Scope 注解结合使用。观察 `MainComponent.java` 的输出，可见 prototype 的作用域生效了：

```
MagicBook
org.example.core.chapter04.PC@4445629
org.example.core.chapter04.PC@25d250c6
```

在自动扫描时，Spring 会取未命名的组件类名，将首字母转小写后作为该 Bean 的标识符。但是在 `MainComponent.java` 中，PC 类却被命名为了 `PC`。

除此之外，我们也可以给 @Component 传值以指定该 Bean 的标识符：

```java
@Component("helloPC")
```

## @Configuration

上个注解 @Component 应用在类上，等价于将类定义成了 Bean；而 @Configuration 应用在类上，则等价于将类变成了“Beans”。

@Configuration 需要与 @Bean 一起使用，前者等价于 `<beans>`，后者等价于 `<bean>`。

由于该注解的作用是替代配置文件，那使用 @Configuration 时自然是可以丢掉一切 XML 文件的。为此，生成 IoC 容器时就要改为用 AnnotationConfigApplicationContext 了：

```java
ApplicationContext context = new AnnotationConfigApplicationContext(SpecificConfig.class);
```

该注解还可以与以下注解搭配使用：
- @ComponentScan：等价于 `<context:component-scan`
- @Import：等价于 `<import>`

应用了 @Bean 注解的方法，其方法名作为 Bean 的标识符，其返回类型作为 Bean 的全限定类名。我们可以通过给注解赋值以修改其标识符。

在 MainConfiguration.java 中，我们期待拿到 MacBook，但还是取到了 MagicBook，这是值 model 被 @Value 覆盖了。