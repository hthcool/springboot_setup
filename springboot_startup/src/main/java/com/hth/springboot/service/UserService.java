package com.hth.springboot.service;

import com.hth.springboot.bean.result.User;

public interface UserService {
    User queryUserById(Integer uid);
}
