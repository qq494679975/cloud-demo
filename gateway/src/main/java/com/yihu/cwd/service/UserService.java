package com.yihu.cwd.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by chenweida on 2017/3/14.
 */
@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;
    //访问失败的回调函数 getUserByIdFallback
    @HystrixCommand(fallbackMethod = "getUserByIdFallback")
    public String getUserById() {
        return restTemplate.getForEntity("http://USER/user/getUserById?userId=1", String.class).getBody();

    }

    //如果調用失敗会调用getUserByIdFallback返回信息
    public String getUserByIdFallback() {
        return "用 @HystrixCommand 注解实现断路器的效果 调用错误";
    }
}
