package com.yihu.cwd.model

import java.util.Date
import javax.persistence._

import org.hibernate.annotations.GenericGenerator

import scala.beans.BeanProperty

/**
  * Created by chenweida on 2017/3/28.
  */
@Entity
@Table(name = "test")
class Test() {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @BeanProperty
  var id=""

  @Column(name = "name")
  @BeanProperty
  var username=""

  @Column(name = "czrq")
  @Temporal(TemporalType.TIMESTAMP)
  @BeanProperty
  var birthday=new Date()

  def this(id:String,username:String,birthday:Date) {
    this()//这个必须要
    this.id=id;
    this.username=username;
    this.birthday=birthday;
  }


  override def toString = s"Test($id, $username, $birthday)"
}
