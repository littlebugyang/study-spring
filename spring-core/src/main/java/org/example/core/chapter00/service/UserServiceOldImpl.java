package org.example.core.chapter00.service;

import org.example.core.chapter00.dao.UserDaoImpl;

public class UserServiceOldImpl implements IUserService {
    private UserDaoImpl userDao = new UserDaoImpl();

    public void deal() {
        System.out.println("业务处理结果：" + userDao.getUser());
    }
}
