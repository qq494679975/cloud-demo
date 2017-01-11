package com.yihu.cwd.user.controller


import com.yihu.cwd.user.model.User
import com.yihu.cwd.user.service.UserService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.{StringRedisTemplate, RedisTemplate}
import org.springframework.mail._
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.web.bind.annotation.{RequestMethod, ResponseBody, RequestMapping, RestController}

/**
  * Created by Administrator on 2016.10.26.
  */
@RestController
@RequestMapping(value = Array("/user"))
@Api(description = "后台-任务控制")
class UserController {
  @Autowired
  private var userService:UserService=_
  @Autowired
  private var redisTemplate: StringRedisTemplate=_
  @Autowired
  private var mailSender: JavaMailSender = _

  @RequestMapping(value = Array("/getUserById"), method = Array(RequestMethod.GET))
  @ResponseBody
  def getUserById(userId:Integer): (User) = {
     userService.getUserById(userId)
  }


}
