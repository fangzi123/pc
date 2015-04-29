package com.micro.rent.pc.entity.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;

import com.micro.rent.pc.util.JedisUtil;


public class RedisCache<K, V> implements Cache<K, V> {

    public static String keyPrefix = "shiro_cache:";
    private String name;

    public RedisCache(String name) {
        this.name = name;
    }

    @Override
    public void clear() throws CacheException {
        String pattern = getCacheKey("*");
        JedisUtil.clear(pattern);
    }

    @Override
    public V get(K key) throws CacheException {
        String k = getCacheKey(key.toString());
        return (V) JedisUtil.get(k);
    }

    private String getCacheKey(String key) {
        return keyPrefix + getName() + ":" + key;
    }

    public String getName() {
        if (name == null) {
            name = "";
        }
        return name;
    }

    @Override
    public Set<K> keys() {
        return JedisUtil.keys(this.keyPrefix + "*");
    }

    @Override
    public V put(K key, V value) throws CacheException {
        String k = getCacheKey(key.toString());
        JedisUtil.set(k, value);
        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
        String k = getCacheKey(key.toString());
        V value = (V) JedisUtil.get(k);
        JedisUtil.del(k);
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int size() {
        String key = keyPrefix + "*";
        Long size = JedisUtil.getRedisTemplate().opsForValue().size(key);
        return size.intValue();
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = keys();
        if (!CollectionUtils.isEmpty(keys)) {
            List<V> values = new ArrayList<V>(keys.size());
            for (K key : keys) {
                V value = get(key);
                if (value != null) {
                    values.add(value);
                }
            }
            return Collections.unmodifiableList(values);
        } else {
            return Collections.emptyList();
        }
    }

}
