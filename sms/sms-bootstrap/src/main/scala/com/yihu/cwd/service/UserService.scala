package com.yihu.cwd.service

import com.yihu.cwd.dao.UserDao
import com.yihu.cwd.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by chenweida on 2017/3/28.
  */
@Service
class UserService {


  @Autowired
  private var userDao: UserDao = _;


  def findUserByLoginName(username: String): User = {
    userDao.findByLoginName(username)
  }


  def findUserById(userId: String) = {
    userDao.findById(userId);
  }
}
