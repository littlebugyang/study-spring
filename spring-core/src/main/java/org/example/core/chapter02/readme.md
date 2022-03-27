# 第 2 章

上一章讲解了如何在配置文件中定义 Bean，这章介绍如何在配置文件中描述 Bean 之间的协作，也就是描述它们的依赖关系。该描述过程可看作是依赖注入的过程。

本章介绍两种注入方式：构造器注入，setter 注入。

## 参考资料
- https://www.bilibili.com/video/BV1WE411d7Dv
- https://docs.spring.io/spring-framework/docs/5.3.17/reference/html/core.html

## 构造器注入
在 XML 中，可以通过下面的几种方法实现构造器注入：
```xml
<constructor-arg ref="someOtherBean"></constructor-arg>

<constructor-arg type="int" value="7500"></constructor-arg>
<constructor-arg type="java.lang.String" value="7500"></constructor-arg>

<constructor-arg index="0" value="7500"></constructor-arg>
<constructor-arg index="1" value="7500"></constructor-arg>

<!-- 还需要额外的 debug flag 或者 @ConstructorProperties 注解 -->
<constructor-arg name="years" value="7500"></constructor-arg>
```

在 `MainConstructorSetterInjection.java` 的例子中，我们通过 constructor injection 给 Mike 赋予了一部手机

## setter 注入

setter 注入体现为 `<bean>` 元素的 `<property>` 子元素：
```xml
<bean id="someID" class="SomeClass">
    <property name="someName" ref="someRef"/>
</bean>
```

使用 setter 注入时，需要注意两点：
- 被注入的类必须有关于被注入属性的 set 方法。即 SomeClass 必须有一个可供调用的 setSomeName 方法。
- 被注入类必须有无参构造函数，哪怕是 private 都关系。

在 `MainConstructorSetterInjection.java` 的例子中，我们通过 setter injection 注入设定了手机的型号。