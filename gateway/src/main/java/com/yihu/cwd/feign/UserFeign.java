package com.yihu.cwd.feign;

import com.yihu.cwd.fallback.UserFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chenweida on 2017/3/14.
 */
// value  userapp的yml中的name
// fallback 回调函数的类
@FeignClient(value = "user", fallback = UserFallback.class)
public interface UserFeign {
    //对应的userapp中的路径   /user/getUserById
    @RequestMapping(method = RequestMethod.GET, value = "/user/getUserById")
    String getUserById();
}
