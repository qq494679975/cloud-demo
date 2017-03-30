package com.yihu.cwd.controller

import com.yihu.cwd.service.{MenuJavaService, UserService}
import com.yihu.cwd.service.system.{RoleService, MenuService}
import io.swagger.annotations.{ApiParam, Api}
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestParam, RequestMethod, RequestMapping, RestController}

/**
  * Created by chenweida on 2017/3/28.
  */
@RestController
@RequestMapping(value = Array("/user"))
@Api(description = "用户")
class UserController extends BaseController{
  @Autowired
  private var userService: UserService = _;
  @Autowired
  private var menuService:MenuService=_;
  @Autowired
  private var roleService:RoleService=_;
  @Autowired
  private var menuJavaService:MenuJavaService=_;

  @RequestMapping(value = Array("/getUserInfo"),method = Array(RequestMethod.GET))
  def getUserInfo(
                   @ApiParam(name = "userId", value = "用户id", required = true) @RequestParam(value = "userId", required = true)userId:String):String={
    try{
      //根据用户id找到用户信息
      var user=userService.findUserById(userId)
      //找到用户的角色所属菜单
      var menus=menuJavaService.findByUserId(userId);

      var js=new JSONObject()
      js.put("user",user)
      js.put("menus",menus)
      write(200,"查询成功","data",js)
    }catch {
      case e: Exception => {
        error(-1, e.getMessage)
      }
    }
  }

}
