package com.yihu.cwd.dao.system

import com.yihu.cwd.model.User
import com.yihu.cwd.model.system.Menu
import org.springframework.data.jpa.repository.{Query, JpaSpecificationExecutor}
import org.springframework.data.repository.PagingAndSortingRepository

/**
  * Created by chenweida on 2017/3/28.
  */
trait MenuDao extends PagingAndSortingRepository[Menu, String] with JpaSpecificationExecutor[Menu] {
  @Query(" from Menu m where m.name=?1 and m.status=1")
  def findByName(name: String): Menu

}
