package com.micro.rent.pc.util;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.RedisTemplate;

public class JedisUtil {
    public static void clear(String pattern) {
        Set<String> keys = util.redisTemplate.keys(pattern);
        if (!keys.isEmpty()) {
            util.redisTemplate.delete(keys);
        }
    }

    public static void del(String key) {
        util.redisTemplate.delete(key);
    }

    public static void expire(String key, long timeout, TimeUnit unit) {
        util.redisTemplate.expire(key, timeout, unit);
    }

    public static Object get(String key) {
        return util.redisTemplate.opsForValue().get(key);
    }

    public static RedisTemplate getRedisTemplate() {
        return util.redisTemplate;
    }

    public static Set keys(Object pattern) {
        return util.redisTemplate.keys(pattern);
    }

    public static void pub(String channel, String message) {
        util.redisTemplate.convertAndSend(channel, message);
    }

    public static void set(String key, Object value) {
        util.redisTemplate.opsForValue().set(key, value);
        expire(key, 30, TimeUnit.MINUTES);
    }

    public static void set(String key, Object value, long timeout, TimeUnit unit) {
        set(key, value);
        expire(key, timeout, unit);
    }

    private RedisTemplate redisTemplate;

    private static JedisUtil util;

    @PostConstruct
    public void init() {
        util = this;
        util.redisTemplate = this.redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}
