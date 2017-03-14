package com.yihu.cwd.controller;

import com.yihu.cwd.feign.UserFeign;
import com.yihu.cwd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by chenweida on 2017/3/14.
 */

@RestController
@RequestMapping("/userHystrix")
public class UserEndPointHystrix {
    @Autowired
    private UserService userService;
    @Autowired
    private UserFeign userFeign;

    /**
     * demo 1
     * 测试Hystrix的断路由效果
     * 启动gatewayApp  访问以下路径 查看日志测试看报错信息
     * 访问路径 http://localhost:8888/userHystrix/getUserByIdDemo1
     * @return
     */
    @RequestMapping(value = "/getUserByIdDemo1", method = RequestMethod.GET)
    public String getUserByIdDemo1() {
        String body=userService.getUserById();
        return body;
    }


    /**
     * demo 2
     * 用 @FeignClient 注解实现断路器的效果
     * 测试Hystrix的断路由效果
     * 启动gatewayApp  访问以下路径 查看日志测试看报错信息
     * 访问路径 http://localhost:8888/userHystrix/getUserByIdDemo2
     * @return
     */
    @RequestMapping(value = "/getUserByIdDemo2", method = RequestMethod.GET)
    public String getUserByIdDemo2() {
        String body=userFeign.getUserById();
        return body;
    }
}
