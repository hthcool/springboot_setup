package com.hth.springboot.controller;

import com.hth.springboot.bean.response.Response;
import com.hth.springboot.service.DAUService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author hantenghui
 * @Date 2020-04-30 10:32
 * @Email hantenghui@tuyoogame.com
 */

@Slf4j
@RestController
public class DAUController {

    @Autowired
    private DAUService dauService;

    @Autowired
    private Response response;

    @RequestMapping(value = "/dau", method = RequestMethod.GET)
    public Response queryDauByDay(@RequestParam String day) {
        System.out.println("接收到请求");
        System.out.println(day);
        return null;
    }

}
