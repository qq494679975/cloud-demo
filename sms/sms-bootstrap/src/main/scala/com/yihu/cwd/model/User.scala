package com.yihu.cwd.model

import java.util.Date
import javax.persistence._

import org.hibernate.annotations.GenericGenerator

import scala.beans.BeanProperty

/**
  * Created by chenweida on 2017/3/28.
  */
@Entity
@Table(name = "user")
class User(){
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @BeanProperty
  var id = ""

  @Column(name = "name")
  @BeanProperty
  var name=""

  @Column(name = "password")
  @BeanProperty
  var password=""

  @Column(name = "sex")
  @BeanProperty
  var sex=1

  @Column(name = "salt")
  @BeanProperty
  var salt=""

  @Column(name = "login_name")
  @BeanProperty
  var loginName=""

  @Column(name = "email")
  @BeanProperty
  var email=""

  @Column(name = "birthday")
  @Temporal(TemporalType.TIMESTAMP)
  @BeanProperty
  var birthday=new Date()

  @Column(name = "create_time")
  @Temporal(TemporalType.TIMESTAMP)
  @BeanProperty
  var createTime=new Date()

  @Column(name = "status")
  @BeanProperty
  var status=""

  def this(id:String,name:String,birthday:Date) {
    this()//这个必须要
    this.id=id;
    this.name=name;
    this.birthday=birthday;
  }

 }

