package com.hth.springboot.controller;

import com.hth.springboot.service.DAUService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author hantenghui
 * @Date 2020-04-30 10:32
 * @Email hantenghui@tuyoogame.com
 */
@RestController
@RequestMapping(value = "/dau")
@Slf4j
public class DAUController {

    @Autowired
    private DAUService dauService;

    @RequestMapping(value = "/{day}/{event}", method = RequestMethod.GET)
    public String queryDauByDay(@PathVariable String day, @PathVariable String event) {
        log.info("接收到请求，day：" + day + "， event：" + event);
        String res = dauService.queryDauByDay(day, event);
        log.info("返回结果是：" + res);
        return res;
    }

}
