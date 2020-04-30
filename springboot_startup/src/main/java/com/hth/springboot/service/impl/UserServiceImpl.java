package com.hth.springboot.service.impl;

import com.hth.springboot.bean.queryresult.User;
import com.hth.springboot.dao.UserDao;
import com.hth.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User queryUserById(Integer uid) {
        return userDao.queryUserById(uid);
    }
}
