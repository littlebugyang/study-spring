package org.example.core.chapter00;

import org.example.core.chapter00.service.IUserService;
import org.example.core.chapter00.service.UserServiceOldImpl;
import org.example.core.chapter00.service.UserServiceOldImpl2;
import org.example.core.chapter00.service.UserServiceOldImpl3;

public class MainOldModified {
    public static void main(String[] args) {
        // 初始需求
        IUserService userService1 = new UserServiceOldImpl();
        userService1.deal();

        // 需求第 1 次变更
        IUserService userService2 = new UserServiceOldImpl2();
        userService2.deal();

        // 需求第 2 次变更
        IUserService userService3 = new UserServiceOldImpl3();
        userService3.deal();
    }
}
