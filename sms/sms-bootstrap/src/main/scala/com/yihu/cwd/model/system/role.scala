package com.yihu.cwd.model.system

import java.util.Date
import javax.persistence._
import org.hibernate.annotations.GenericGenerator

import scala.beans.BeanProperty

/**
  * Created by chenweida on 2017/3/28.
  */
@Entity
@Table(name = "system_role")
class Role  {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @BeanProperty
  var id = ""

  @Column(name = "name")
  @BeanProperty
  var name = ""

  @Column(name = "create_user")
  @BeanProperty
  var createUser = ""

  @Column(name = "create_time")
  @Temporal(TemporalType.TIMESTAMP)
  @BeanProperty
  var createTime = new Date()

  @Column(name = "create_user_name")
  @BeanProperty
  var createUserName = ""

  @Column(name = "modify_user")
  @BeanProperty
  var modifyUser = ""

  @Column(name = "modify_time")
  @Temporal(TemporalType.TIMESTAMP)
  @BeanProperty
  var modifyTime = new Date()

  @Column(name = "modify_user_name")
  @BeanProperty
  var modifyUserName = ""

  @Column(name = "status")
  @BeanProperty
  var status = "" //状态 1可用 0删除
}
