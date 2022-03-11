package org.example.core.chapter00;

import org.example.core.chapter00.dao.IUserDao;
import org.example.core.chapter00.dao.UserDaoImpl;
import org.example.core.chapter00.dao.UserDaoMongoImpl;
import org.example.core.chapter00.dao.UserDaoMySqlImpl;
import org.example.core.chapter00.service.*;

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
