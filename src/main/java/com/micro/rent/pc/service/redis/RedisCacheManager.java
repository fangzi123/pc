package com.micro.rent.pc.service.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import com.micro.rent.pc.entity.vo.RedisCache;


public class RedisCacheManager implements CacheManager {

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new RedisCache<K, V>(name);
    }

}
