package com.yihu.cwd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenweida on 2017/4/13.
 * 修改完之后 项目会自动重启
 * 不管是修改类，方法 还是代码
 */
@RestController
public class Controller {
    @GetMapping("/hello")
    public String hello() {
        return "你好呀123123";
    }
}
