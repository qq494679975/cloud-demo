package com.cwd.auth2.dao;

import com.cwd.auth2.entity.Users;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2017/1/11.
 */
public interface UserDao extends PagingAndSortingRepository<Users, String>, JpaSpecificationExecutor<Users> {
    @Query(" from Users u where u.user=?1 ")
    Users findByUser(String username);
}
