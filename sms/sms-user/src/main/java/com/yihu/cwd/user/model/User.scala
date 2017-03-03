package com.yihu.cwd.user.model

import javax.persistence._
import java.util.Date
import scala.beans.BeanProperty

/**
  * Created by Administrator on 2016.10.26.
  */
@Entity
@Table(name = "user")
class User() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @BeanProperty
    var id=1

    @Column(name = "name")
    @BeanProperty
    var username=""

    @Column(name = "czrq")
    @Temporal(TemporalType.TIMESTAMP)
    @BeanProperty
    var birthday=new Date()

    def this(id:Int,username:String,birthday:Date) {
        this()//这个必须要
        this.id=id;
        this.username=username;
        this.birthday=birthday;
    }


    override def toString = s"User($id, $username, $birthday)"
}
