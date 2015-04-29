package com.micro.rent.common.comm;

import com.micro.rent.common.support.ApplicationContextUtil;
import com.micro.rent.common.support.CacheManagerHelper;

import java.io.Serializable;


/**
 * @author
 * @version 1.0
 * @Description 系统缓存枚举类
 * @date 2013-4-10
 */
public enum ECache {
    /**
     *
     */
    CACHE_XSTREAM("cache.xstream");


    private static CacheManagerHelper cacheManagerHelper = ApplicationContextUtil.getBean(CacheManagerHelper.class);
    private final String code;

    ECache(String code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param key
     * @return
     * @Description 通过缓存键值获取当前枚举缓存中的缓存值
     * @author
     */
    public <T> T getCacheEntity(Serializable key) {
        return (T) cacheManagerHelper.get(getCode(), key);
    }

    /**
     * @param key
     * @param value
     * @Description 获取当前枚举缓存并进行缓存设置
     * @author
     */
    public void putCacheEntity(Serializable key, Object value) {
        cacheManagerHelper.put(getCode(), key, value);
    }

    public void removeCache() {
        cacheManagerHelper.clearCache(getCode());
    }
}
