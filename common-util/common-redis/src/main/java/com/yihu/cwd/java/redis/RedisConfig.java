package com.yihu.cwd.java.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016.10.26.
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;

//    public CacheResolver cacheResolver() {
//        return new CacheResolver();
//    }

    @Value("${spring.redis.timeout}")

    @Bean
    public KeyGenerator wiselyKeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                //key的构造器 包名+类名+函数名+函数的参数名字
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName().replace(".",":"));
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(":");
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };

    }

    /**
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(
            StringRedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    /**
     * 设置字符串的redis工具类
     * @param factory
     * @return
     */
    @Bean
    public StringRedisTemplate redisTemplate(
            RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
