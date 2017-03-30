package com.yihu.cwd.model.system

import javax.persistence._

import org.hibernate.annotations.GenericGenerator

import scala.beans.BeanProperty

/**
  * Created by chenweida on 2017/3/28.
  */
@Entity
@Table(name = "system_role_menu")
class RoleMenu  {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @BeanProperty
  var id = ""


  @Column(name = "role_id")
  @BeanProperty
  var roleId = ""

  @Column(name = "menu_id")
  @BeanProperty
  var menuId = ""
}
