package com.yihu.cwd.user.model

import javax.persistence._
import java.util.Date
import scala.beans.BeanProperty

/**
  * Created by Administrator on 2016.10.26.
  */
@Entity
@Table(name = "wlyy_user")
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

}
