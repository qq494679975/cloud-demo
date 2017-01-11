package com.cwd.auth2.controller;

import com.cwd.auth2.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/1/11.
 */

@Api(description = "登陆")
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登陆")
    @RequestMapping(value = "/userLogin", method = RequestMethod.GET)
    public UserDetails login(String username, String password) {
        UserDetails userDetails = userService.loadUserByUsername(username);

        return userDetails;
    }
}
