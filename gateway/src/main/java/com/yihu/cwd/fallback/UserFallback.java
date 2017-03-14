package com.yihu.cwd.fallback;

import com.yihu.cwd.feign.UserFeign;
import org.springframework.stereotype.Component;

/**
 * Created by chenweida on 2017/3/14.
 */
@Component
public class UserFallback implements UserFeign{

    @Override
    public String getUserById() {
        return "用 @FeignClient 注解实现断路器的效果 调用错误";
    }
}
