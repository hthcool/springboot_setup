package com.hth.springboot.dao;

import com.hth.springboot.bean.queryresult.User;

public interface UserDao {
    User  queryUserById(Integer id);
}
