package com.yihu.cwd.service.system

import java.util
import javax.transaction.Transactional

import com.yihu.cwd.dao.system.{UserRoleDao, RoleDao}
import com.yihu.cwd.model.system.{Menu, UserRole}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.{BeanPropertyRowMapper, JdbcTemplate}
import org.springframework.stereotype.Service

import scala.collection.mutable

/**
  * Created by chenweida on 2017/3/28.
  */
@Service
class RoleService {
  @Autowired
  private var roleDao:RoleDao=_;
  @Autowired
  private var userRoleDao:UserRoleDao=_;


  @Transactional
  def add(name: String, remark: String) = {

  }

}
