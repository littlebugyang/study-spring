package org.example.core.chapter00.service;

import org.example.core.chapter00.dao.IUserDao;

public class UserServiceNewImpl implements IUserService {
    private IUserDao userDao;

    public UserServiceNewImpl(IUserDao userDao) {
        this.userDao = userDao;
    }

    public void deal() {
        System.out.println("业务处理结果：" + userDao.getUser());
    }
}
