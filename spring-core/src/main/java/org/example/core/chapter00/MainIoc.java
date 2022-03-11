package org.example.core.chapter00;

import org.example.core.chapter00.dao.IUserDao;
import org.example.core.chapter00.service.IUserService;
import org.example.core.chapter00.service.UserServiceNewImpl;

import java.lang.reflect.InvocationTargetException;

public class MainIoc {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String name = "org.example.core.chapter00.dao.UserDaoImpl";

        // 初始需求
        IUserService userService1 = new UserServiceNewImpl(
                (IUserDao) Class.forName(name).getConstructors()[0].newInstance()
        );
        userService1.deal();

        // 需求第 1 次变更
        name = "org.example.core.chapter00.dao.UserDaoMySqlImpl";
        IUserService userService2 = new UserServiceNewImpl(
                (IUserDao) Class.forName(name).getConstructors()[0].newInstance()
        );
        userService2.deal();

        // 需求第 2 次变更
        name = "org.example.core.chapter00.dao.UserDaoMongoImpl";
        IUserService userService3 = new UserServiceNewImpl(
                (IUserDao) Class.forName(name).getConstructors()[0].newInstance()
        );
        userService3.deal();
    }
}
