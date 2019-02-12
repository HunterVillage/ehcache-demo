package com.rever.ehcachedemo.commoncache.map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rever.ehcachedemo.commoncache.ICache;


import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: ConcurrentMap cache实现类型
 * @Author: gaoyakang
 * @Version: 1.0
 * @Create Date Time: 2018-08-17 11:23
 * @Update Date Time:
 * @see
 */
public class ConcurrentMapCache<T> implements ICache<T> {

    private Map<String, T> cacheMap = Maps.newConcurrentMap();

    @Override
    public Boolean exists(String key) {
        return cacheMap.containsKey(key);
    }

    @Override
    public T get(String key) {
        return cacheMap.get(key);
    }

    @Override
    public List<T> get(Set<String> keys) {
        List<T> list = Lists.newArrayList();
        keys.forEach(key-> list.add(cacheMap.get(key)));
        return list;
    }

    @Override
    public Set<String> getKeys() {
        final Set<String> set = cacheMap.keySet();
        return set;
    }

    @Override
    public Boolean put(String key, T t) {
        cacheMap.put(key, t);
        return true;
    }

    @Override
    public Boolean put(Map<String, T> map) {
        cacheMap.putAll(map);
        return true;
    }

    @Override
    public Boolean putIfAbsent(String key, T t) {
        if(!cacheMap.containsKey(key)){
            this.put(key, t);
        }
        return true;
    }

    @Override
    public Boolean putIfAbsent(Map<String, T> map) {
        map.forEach(this::putIfAbsent);
        return true;
    }

    @Override
    public Boolean remove(String key) {
        cacheMap.remove(key);
        return true;
    }

    @Override
    public Boolean remove(Set<String> keys) {
        keys.forEach(key-> cacheMap.remove(key));
        return true;
    }

    @Override
    public Boolean update(String key, T t) {
        cacheMap.put(key, t);
        return true;
    }

    @Override
    public Boolean update(Map<String, T> map) {
        cacheMap.putAll(map);
        return true;
    }

    @Override
    public Boolean clean() {
        cacheMap.clear();
        return true;
    }


    @Override
    public Map<String, T> getOriginal() {
        return cacheMap;
    }
}
