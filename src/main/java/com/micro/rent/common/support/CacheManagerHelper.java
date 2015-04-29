package com.micro.rent.common.support;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 缓存管理器
 *
 * @author
 */
@Component
public class CacheManagerHelper {

    @Autowired
    private CacheManager cacheManager;

    /**
     * 从缓存中获取对象
     *
     * @param cache_name
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String cache_name, Serializable key) {
        Cache cache = getCache(cache_name);
        if (cache != null) {
            Element elem = cache.get(key);
            if (elem != null && !cache.isExpired(elem))
                return (T) elem.getObjectValue();
        }
        return null;
    }

    /**
     * 把对象放入缓存中
     *
     * @param cache_name
     * @param key
     * @param value
     */
    public synchronized void put(String cache_name, Serializable key, Object value) {
        Cache cache = getCache(cache_name);
        if (cache != null) {
            cache.remove(key);
            Element elem = new Element(key, value);
            cache.put(elem);
        }
    }

    /**
     * 获取指定名称的缓存
     *
     * @param arg0
     * @return
     * @throws IllegalStateException
     */
    public Cache getCache(String cache) throws IllegalStateException {
        return cacheManager.getCache(cache);
    }


    /**
     * 清除指定名称的缓存的所有key
     *
     * @param arg0
     * @return
     * @throws IllegalStateException
     */
    public synchronized void clearCache(String cache) throws IllegalStateException {
        if (cacheManager.cacheExists(cache)) {
            cacheManager.getCache(cache).removeAll();
        }
    }

    /**
     * 清除所有缓存的所有key
     *
     * @param arg0
     * @return
     * @throws IllegalStateException
     */
    public synchronized void clearAllCache() throws IllegalStateException {
        for (String cacheName : cacheManager.getCacheNames()) {
            cacheManager.getCache(cacheName).removeAll();
        }
    }


    /**
     * 获取缓存中的信息
     *
     * @param cache
     * @param key
     * @return
     * @throws IllegalStateException
     * @throws CacheException
     */
    public Element getElement(String cache, Serializable key) throws IllegalStateException, CacheException {
        Cache cCache = getCache(cache);
        return cCache.get(key);
    }
}
