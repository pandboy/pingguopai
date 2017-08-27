package com.xfl.pingguopai.common;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by timely
 */
public class LocalCache<K, V> {
    private Cache<K, V> cache;
    private String cacheName;

    private final long MAX_SIZE = 1024 * 1024;

    private final long EXPIRE_TIME_ONE_HOUR = 1 * 60 * 60 * 1000;

    public LocalCache(String cacheName, long maxSize, long maxLifeTime) {
        this.cacheName = cacheName;
        if (maxSize < 0) {
            maxSize = MAX_SIZE;
        }
        if (maxLifeTime < 0) {
            maxLifeTime = EXPIRE_TIME_ONE_HOUR;
        }
        cache = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .expireAfterWrite(maxLifeTime, TimeUnit.MILLISECONDS)
                .build();
    }

    public V put(K key, V value) {
        cache.put(key, value);
        return value;
    }

    public V get(K key) {
        return cache.getIfPresent(key);
    }

    /**
     * 返回旧的值
     * @param key
     * @return
     */
    public V remove(K key) {
        V oldV = get(key);
        cache.invalidate(key);
        return oldV;
    }

    public void clear() {
        cache.invalidateAll();
    }

    public int size() {
        return (int)cache.size();
    }

    public boolean isEmpty() {
        return cache.size() == 0;
    }

    /**
     * 获取所有缓存值
     * @return
     */
    public Collection<V> values() {
        return cache.asMap().values();
    }

    public boolean containsKey(K key) {
        return cache.asMap().containsKey(key);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        cache.putAll(map);
    }

    public boolean containsValue(V value) {
        return cache.asMap().containsValue(value);
    }

    public int getCacheSize() {
        return (int) cache.size();
    }

    public Set<K> keySet() {
        return cache.asMap().keySet();
    }
}
