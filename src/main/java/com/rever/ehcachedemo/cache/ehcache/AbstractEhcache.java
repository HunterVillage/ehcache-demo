package com.rever.ehcachedemo.cache.ehcache;

import com.google.common.collect.Lists;
import com.rever.ehcachedemo.cache.ICache;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: Ehcache 抽象缓存实现类, 子类继承它使用
 * @Author: gaoyakang
 * @Version: 1.0
 * @Create Date Time: 2018-08-13 14:27
 * @Update Date Time:
 *
 * @see
 */
public abstract class AbstractEhcache<T> implements ICache<T> {

    /**
     * key 拼接符号
     */
    private final String JOINING = "_";

    private Cache cache;

    private final String keyPrefix;

    public AbstractEhcache(CacheManager cacheManager){
        final String cacheName = cacheName();
        Objects.requireNonNull(cacheName, "cacheName cannot be null");
        this.cache = cacheManager.getCache(cacheName);
        Objects.requireNonNull(this.cache, cacheName + " not found");
        final String s = keyPrefix();
        if(s == null){
            this.keyPrefix = "";
        } else {
            this.keyPrefix = s;
        }
    }

    /**
     * 缓存名称，配置文件中设置的缓存名称
     * @return
     */
    abstract String cacheName();

    /**
     * key 前缀, 主要解决id重复。如果为null或者是空串, 则使用原始key
     * @return
     */
    abstract String keyPrefix();


    @Override
    public Boolean exists(String key) {
        final Element element = cache.get(joiningKey(key));
        if(element == null){
            return false;
        }
        return true;
    }

    @Override
    public T get(String key) {
        Element element = cache.get(joiningKey(key));
        if(element == null){
            return null;
        }
        return (T)element.getObjectValue();
    }

    @Override
    public List<T> get(Set<String> keys) {
        if(keys == null){
            return Lists.newArrayList();
        }
        return keys.stream()
                .map(this::get)
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getKeys() {
        final List<String> keys = cache.getKeys();
        if(keys == null){
            return Collections.emptySet();
        }
        return keys.stream()
                .filter(key -> key != null && Objects.equals(getKeyPrefixByJoiningKey(key), keyPrefix))
                .map(this::getOriginalKey)
                .collect(Collectors.toSet());
    }

    @Override
    public Boolean put(String key, T value) {
        if(key == null){
            return false;
        }
        cache.put(new Element(joiningKey(key), value));
        return true;
    }

    @Override
    public Boolean put(Map<String, T> map) {
        if(map == null){
            return false;
        }
        map.forEach(this::put);
        return true;
    }

    @Override
    public Boolean putIfAbsent(String key, T t) {
        cache.putIfAbsent(new Element(joiningKey(key), t), true);
        return true;
    }

    @Override
    public Boolean putIfAbsent(Map<String, T> map) {
        if(map == null){
            return false;
        }
        map.forEach(this::putIfAbsent);
        return true;
    }

    @Override
    public Boolean remove(String key) {
        return cache.remove(joiningKey(key));
    }

    @Override
    public Boolean remove(Set<String> keys) {
        keys.stream()
                .map(this::joiningKey)
                .forEach(this::remove);
        return true;
    }

    @Override
    public Boolean update(String key, T t) {
        return this.put(key, t);
    }

    @Override
    public Boolean update(Map<String, T> map) {
        return this.put(map);
    }

    @Override
    public Boolean clean() {
        cache.removeAll();
        return true;
    }

    @Override
    public Cache getOriginal() {
        return cache;
    }

    /**
     * 拼接key
     * @param originalKey 原始key
     * @return 返回拼接的key
     */
    private String joiningKey(String originalKey){
        if(Objects.equals(keyPrefix, "")){
            return originalKey;
        } else {
            return keyPrefix + JOINING + originalKey;
        }
    }

    /**
     * 通过拼接的key获得原始key
     * @param joiningKey 拼接的key
     * @return 返回原始key
     */
    private String getOriginalKey(String joiningKey){
        if(joiningKey == null){
            return null;
        }
        if(joiningKey.contains(JOINING)){
            final String[] split = joiningKey.split(JOINING);
            if(split.length == 2){
                return split[1];
            } else {
                return joiningKey;
            }
        } else {
            return joiningKey;
        }
    }

    /**
     * 通过拼接的key得到key前缀
     * @param joiningKey
     * @return
     */
    private String getKeyPrefixByJoiningKey(String joiningKey){
        if(joiningKey == null){
            return null;
        }
        final String[] split = joiningKey.split(JOINING);
        if(split.length == 2){
            return split[0];
        } else {
            return joiningKey;
        }
    }

}
