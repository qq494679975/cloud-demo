package com.yihu.cwd.java.redis.usedemo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2016.10.26.
 */
@Service
public class DemoService {
    /**
     * @Cacheable注解有三个参数，value是必须的，还有key和condition。
     * 第一个参数，也就是value指明了缓存将被存到什么地方。
     * Spring默认使用被@Cacheable注解的方法的签名来作为key，当然你可以重写key，自定义key可以使用SpEL表达式
     * @Cacheable(value = "employee", key = "#username")
     * @Cacheable(value = "employee", condition = "#id < 25")
     * @param id
     * @return
     */
    @Cacheable(value = "reportcache", keyGenerator = "wiselyKeyGenerator")
    public User getUser(int id) {
        System.out.println("无缓存的时候调用这里---数据库查询");
        return new User(1,"test",new Date());
    }
}
