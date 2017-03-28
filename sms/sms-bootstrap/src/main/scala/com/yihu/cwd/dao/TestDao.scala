package com.yihu.cwd.dao

import com.yihu.cwd.model.Test
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository

/**
  * Created by chenweida on 2017/3/28.
  */
trait TestDao extends PagingAndSortingRepository[Test,String] with JpaSpecificationExecutor[Test]{

}
