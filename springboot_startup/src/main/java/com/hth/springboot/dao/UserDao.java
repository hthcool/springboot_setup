package com.hth.springboot.dao;

import com.hth.springboot.bean.result.User;

public interface UserDao {
    User  queryUserById(Integer id);
}
