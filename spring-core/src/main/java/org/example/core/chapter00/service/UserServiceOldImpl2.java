package org.example.core.chapter00.service;

import org.example.core.chapter00.dao.UserDaoMySqlImpl;

public class UserServiceOldImpl2 implements IUserService {
    private UserDaoMySqlImpl userDao = new UserDaoMySqlImpl();

    public void deal() {
        System.out.println("业务处理结果：" + userDao.getUser());
    }
}
