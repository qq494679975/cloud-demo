package com.yihu.cwd.user.service

import java.util.Date

import com.yihu.cwd.user.dao.UserDao
import com.yihu.cwd.user.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
/**
  * Created by Administrator on 2016.10.26.
  */
@Service
class UserService {
  @Autowired
  private var userDao:UserDao=_;

  @Cacheable(value = Array("user_key_id"), keyGenerator = "wiselyKeyGenerator")
  def getUserById(userId: Integer): (User) = {
    userDao.findOne(userId)
  }
}
