package com.yihu.cwd.dao.system

import com.yihu.cwd.model.system.{RoleMenu, Menu}
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository

/**
  * Created by chenweida on 2017/3/28.
  */
trait RoleMenuDao extends PagingAndSortingRepository[RoleMenu,String] with JpaSpecificationExecutor[RoleMenu]{

}
