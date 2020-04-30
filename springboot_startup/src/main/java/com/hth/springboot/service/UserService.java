package com.hth.springboot.service;

import com.hth.springboot.bean.queryresult.User;

public interface UserService {
    User queryUserById(Integer uid);
}
