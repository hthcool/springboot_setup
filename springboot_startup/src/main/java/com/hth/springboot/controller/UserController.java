package com.hth.springboot.controller;

import com.hth.springboot.bean.queryresult.User;
import com.hth.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    // private Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public List<User> queryList() {

        List<User> res = new ArrayList<>();

        res.add(new User());

        return res;
    }

    @PostMapping("/")
    public String createUser(@ModelAttribute User userParams) {
        System.out.println(userParams);
        return "success";
    }

    @GetMapping(value = "/{id}")
    public User queryUserById(@PathVariable Integer id) {
        log.info("接收到查询请求，请求路径为/user/" + id);
        User user = userService.queryUserById(id);
        log.warn("返回数据为" + user);
        log.error("返回数据为" + user);
        return user;
    }

}
