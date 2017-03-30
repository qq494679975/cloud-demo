package com.yihu.cwd.dao

import com.yihu.cwd.model.{User, Test}
import org.springframework.data.jpa.repository.{Query, JpaSpecificationExecutor}
import org.springframework.data.repository.PagingAndSortingRepository

/**
  * Created by chenweida on 2017/3/28.
  */
trait UserDao extends PagingAndSortingRepository[User, String] with JpaSpecificationExecutor[User] {
  @Query(" from User u where u.id=?1 and u.status=1 ")
  def findById(userId: String) :User

  @Query(" from User u where u.loginName=?1 and u.status=1 ")
  def findByLoginName(username: String): User

}
