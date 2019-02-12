package com.rever.ehcachedemo.commoncache;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @Description: 缓存工具类
 * @Author: gaoyakang
 * @Version: 1.0
 * @Create Date Time: 2019-01-31 14:16
 * @Update Date Time:
 * @see
 */
public final class CacheUtil {


    private CacheUtil(){}

    /**
     * 向 ICache<Map<String, T>> 类型的缓存中添加数据
     * @param cache 缓存实体
     * @param cacheKey 缓存key
     * @param mapKey mapkey
     * @param t 具体保存的数据
     */
    public static <T> void putMapToCache(ICache<Map<String, T>> cache, String cacheKey, String mapKey, T t){
        Map<String, T> map = cache.get(cacheKey);
        if(map == null){
            map = Maps.newHashMap();
            map.put(mapKey, t);
            cache.put(cacheKey, map);
        } else {
            map.put(mapKey, t);
            cache.put(cacheKey, map);
        }
    }

    /**
     * 向 ICache<Map<String, T>> 类型的缓存中移除数据
     * @param cache 缓存实体
     * @param cacheKey 缓存key
     * @param mapKey mapkey
     */
    public static <T> void removeMapFormCache(ICache<Map<String, T>> cache, String cacheKey,String mapKey){
        Map<String, T> map = cache.get(cacheKey);
        if(map != null){
            map.remove(mapKey);
            cache.put(cacheKey, map);
        }
    }

}
