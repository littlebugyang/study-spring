# 第 0 章

## 参考资料
- https://www.bilibili.com/video/BV1WE411d7Dv
- https://www.zhihu.com/question/24304289/answer/147529485
- 《Java EE 互联网轻量级框架整合开发》——杨开振，等

## 简单的 MVC 程序

一个简单的 MVC 架构风格的程序可能由 controller、dao、service 这些字样的包名组成的。

controller 负责响应视图层的行为，并将请求转发给相应的业务逻辑 service，而后者依托于 dao 进行数据库对象和业务数据对象的映射。

如代码所示，一个负责获取用户信息的业务逻辑实现 UserServiceImpl，其聚合了一个 IUserDao 成员，service 可以调用该成员进行数据库的访问，并将映射回来的结果返回给上一层：
```java
public class UserServiceOldImpl implements IUserService {
    private UserDaoImpl userDao = new UserDaoImpl();

    public void deal() {
        System.out.println("业务处理结果：" + userDao.getUser());
    }
}
```

在 MainOld.java 中，我们用 main 函数来模拟 controller / 真实用户的调用：
```java
public class MainOld {
    // 用 main 函数来充当外部调用和 controller
    public static void main(String[] args) {
        IUserService userService = new UserServiceOldImpl();
        userService.deal();
    }
}
```

## 业务发展

现需要增加一种 IUserDao 的实现方式，姑且称之为 UserDaoMySqlImpl，并应用之，那 UserServiceOldImpl 就必须改写：IUserDao 成员实例化为新的 UserDaoMySqlImpl：
```java
public class UserServiceOldImpl2 implements IUserService {
    private UserDaoMySqlImpl userDao = new UserDaoMySqlImpl();
    // ...
}
```

假如，业务再扩张，增加新的实现方式 UserDaoMongoImpl 呢？那又得再次修改 service 的内部代码了：
```java
public class UserServiceOldImpl3 implements IUserService {
    private UserDaoMongoImpl userDao = new UserDaoMongoImpl();
    // ...
}
```

不难发现，每次增加 dao，除了增加新的 dao 层代码，我们还要把原有的 service 代码也修改一遍。

我们希望跟 service 类的关系可以变成“我给啥，你用啥”，可以做这样的修改：把 service 类的 dao 类型改为抽象类，并且设置有参构造函数，或者设置 set 方法以修改 IUserDao 成员：
```java
public class UserServiceNewImpl implements IUserService {
    private IUserDao userDao;

    UserServiceNewImpl(IUserDao userDao) {
        this.userDao = userDao;
    }
    // ...
}
```

这样，我们就可以通过给 service 传入实现了同样接口的 dao 实现类来避免 service 类的频繁修改了。

这里对 service 类的优化，实际上是转移了“选择 dao 实现类”这一行为。在修改后的 MainNew 中，这一选择仍需依赖硬编码，具体表现为各种“new 对象”：
```java
public class MainNew {
    public static void main(String[] args) {
        // 初始需求
        IUserDao userDao1 = new UserDaoImpl();
        IUserService userService1 = new UserServiceNewImpl(userDao1);
        userService1.deal();

        // 需求第 1 次变更
        IUserDao userDao2 = new UserDaoMySqlImpl();
        IUserService userService2 = new UserServiceNewImpl(userDao2);
        userService2.deal();

        // 需求第 2 次变更
        IUserDao userDao3 = new UserDaoMongoImpl();
        IUserService userService3 = new UserServiceNewImpl(userDao3);
        userService3.deal();
    }
}
```

使用 new 语句时，创建什么对象，由开发人员硬编码，程序被动接受创建目标。

除 new 以外，在 Java 中还可利用反射（reflection）来创建对象，可参考关于反射，可以参考[知乎的这篇文章](https://www.zhihu.com/question/24304289/answer/147529485)以了解其原理。

该特性允许程序在运行时决定创建内容，此时创建对象主动方变成了程序，所以谓之“控制反转”。

## 控制反转

```java
public class MainIoc {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String name = "org.example.core.chapter00.dao.UserDaoImpl";

        // 初始需求
        IUserService userService1 = new UserServiceNewImpl(
                (IUserDao) Class.forName(name).getConstructors()[0].newInstance()
        );
        userService1.deal();
        // ...
    }
}
```

通过实现控制反转，我们可以减少 new dao 对象的次数。更重要的，每次需求变更，除新增 dao 实现类外，我们需要修改的仅有类的全限定名，在此基础上，我们可以转而使用配置文件等可维护性更强的方法来辅助业务更新。