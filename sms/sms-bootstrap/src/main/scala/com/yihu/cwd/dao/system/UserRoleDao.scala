package com.yihu.cwd.dao.system

import com.yihu.cwd.model.system.{UserRole, Menu}
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository

/**
  * Created by chenweida on 2017/3/28.
  */
trait UserRoleDao extends PagingAndSortingRepository[UserRole, String] with JpaSpecificationExecutor[UserRole] {

}
