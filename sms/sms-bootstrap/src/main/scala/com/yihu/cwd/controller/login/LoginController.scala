package com.yihu.cwd.controller.login

import com.yihu.cwd.controller.BaseController
import com.yihu.cwd.model.User
import com.yihu.cwd.service.UserService
import com.yihu.cwd.service.system.MenuService
import com.yihu.cwd.util.MD5
import io.swagger.annotations.{ApiParam, ApiOperation, Api}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMethod, RestController, RequestParam, RequestMapping}

/**
  * Created by chenweida on 2017/3/28.
  */
@RestController
@Api(description = "/登陆")
class LoginController extends BaseController {
  @Autowired
  private var userService: UserService = _;
  @Autowired
  private var menuService:MenuService=_;

  @ApiOperation(value = "登陆")
  @RequestMapping(value = Array("/login"), method = Array(RequestMethod.GET))
  def login(
             @ApiParam(name = "username", value = "用户名", required = true) @RequestParam(value = "username", required = true) username: String,
             @ApiParam(name = "password", value = "密码", required = true) @RequestParam(value = "password", required = true) password: String)
  : String = {
    //用户信息
    var user = userService.findUserByLoginName(username);
    if (user == null) {
      return error(-1, "用户不存在")
    }
    if (!user.getPassword.equals(MD5.GetMD5Code(password + user.getSalt))) {
      return error(-1, "账号密码错误")
    }
    return write(200, "请求成功", "user", user)
  }
}
