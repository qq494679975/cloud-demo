package com.yihu.cwd.controller;

import com.yihu.cwd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by chenweida on 2017/3/13.
 */
@RestController
@RequestMapping("/userRibbon")
public class UserEndPointRibbon {
    @Autowired
    RestTemplate restTemplate;

    /**
     * 测试ribbon的负载均衡效果
     * 启动2个userapp 端口不一样
     * 启动gatewayApp  访问以下路径 查看日志测试负载均衡
     * 访问路径 http://localhost:8888/userRibbon/getUserById
     * @return
     */
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public String getUserById() {
        String body=restTemplate.getForEntity("http://USER/user/getUserById?userId=1", String.class).getBody();
        return body;
    }
}
