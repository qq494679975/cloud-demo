package com.yihu.cwd.user.dao

import com.yihu.cwd.user.model.User
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
/**
  * Created by Administrator on 2016.10.26.
  */
trait UserDao extends PagingAndSortingRepository[User,Integer] with JpaSpecificationExecutor[User]{

}
