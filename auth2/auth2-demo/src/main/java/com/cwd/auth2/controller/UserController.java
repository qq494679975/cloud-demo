package com.cwd.auth2.controller;

import com.cwd.auth2.entity.Users;
import com.cwd.auth2.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/1/11.
 */
@Api(description = "用户管理")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public Users getUserInfo(String id) {
        Users userInfo = new Users();
        return userInfo;
    }

}
