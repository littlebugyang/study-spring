package org.example.core.chapter00;

import org.example.core.chapter00.service.IUserService;
import org.example.core.chapter00.service.UserServiceOldImpl;

public class MainOld {
    // 用 main 函数来充当外部调用和 controller
    public static void main(String[] args) {
        IUserService userService = new UserServiceOldImpl();
        userService.deal();
    }
}
