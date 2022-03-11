package org.example.core.chapter00.service;

import org.example.core.chapter00.dao.UserDaoMongoImpl;

public class UserServiceOldImpl3 implements IUserService {
    private UserDaoMongoImpl userDao = new UserDaoMongoImpl();

    public void deal() {
        System.out.println("业务处理结果：" + userDao.getUser());
    }
}
