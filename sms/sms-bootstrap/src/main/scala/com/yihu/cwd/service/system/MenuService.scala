package com.yihu.cwd.service.system

import java.util
import javax.transaction.Transactional

import com.yihu.cwd.dao.system.MenuDao
import com.yihu.cwd.model.system.{UserRole, Menu}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.{BeanPropertyRowMapper, JdbcTemplate}
import org.springframework.stereotype.Service

import scala.collection.mutable

/**
  * Created by chenweida on 2017/3/28.
  */
@Service
class MenuService {
  @Autowired
  private var menuDao: MenuDao = _;
  @Autowired
  private var jdbcTemplate:JdbcTemplate=_;


  @Transactional
  def add(name: String, url: String, remark: String,fid:String) = {
    var menuObj = menuDao.findByName(name);
    if (menuObj != null) {
      throw new Exception("菜单名称存在")
    }

    var menu = new Menu();
    menu.setRemark(remark)
    menu.setName(name)
    menu.setUrl(url)
    menu.setFid(fid)
    menuDao.save(menu)
  }

  def main(args: Array[String]) {

  }
}
